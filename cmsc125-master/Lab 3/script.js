/**
 * ? ------------------------------------------------------
 * * ------------- VARIABLE DECLARATIONS ------------------
 * ? ------------------------------------------------------
 */

const jobMaxNumber = 25;
const time = [5, 4, 8, 2, 2, 6, 8, 10, 7, 6, 5, 8, 9, 10, 10, 7, 3, 1, 9, 3, 7, 2, 8, 5, 10];
const jobSize = [5760, 4190, 3290, 2030, 2550, 6990, 8940, 740, 3930, 6890, 6580, 3820, 9140, 420, 220, 7540, 3210, 1380, 9850, 3610, 7540, 2710, 8390, 5950, 760];

const memoryMaxNumber = 10;
const memorySize = [9500, 7000, 4500, 8500, 3000, 9000, 1000, 5500, 1500, 500];
const maxMemoryBlock = 9500;

let time_count = 0;

let arr_jobs = [];
let arr_memoryBlocks = [];

let arr_throughput = [];
let arr_partitions_used = [];
let arr_partitions_not_used = [];
let arr_waiting_queue = [];
let arr_waiting_time = [];
let arr_blocks_internal_fragmentation = [];
let arr_blocks_internal_fragmentation_unused_space = [];

/**
 * ? ------------------------------------------------------
 * * --------------- DEFINE CLASSES -----------------------
 * ? ------------------------------------------------------
 */

class Job {
    constructor(jobNumber, time, jobSize) {
        this.jobNumber = jobNumber;
        this.time = time;
        this.jobSize = jobSize;

        this.status = "Waiting"; // Waiting, Processing, Finished
        this.currentMemoryOccupying = "None"; // The current memory block where the job is occupying
        this.timeLeft = time;
        this.timeWaiting = 0;
    }
}

class Memory {
    constructor(memoryNumber, memorySize) {
        this.memoryNumber = memoryNumber;
        this.memorySize = memorySize;

        this.currentJobsOccupying = []; // An array of jobs occupying the memory block
        this.currentMemorySize = memorySize; // Size left if jobs are occupying the memory block
    }
}

/**
 * ? ------------------------------------------------------
 * * ------------------ OTHER FUNCTIONS -------------------
 * ? ------------------------------------------------------
 */

// Refresh page
function reset() {
    window.location.reload();
}

// Populate jobs
function create_job_object() {
    for (let i = 0; i < jobMaxNumber; i++) {
        let jobNumber = i + 1;
        arr_jobs.push(new Job(jobNumber, time[i], jobSize[i]));
    }
}

// Populate memory blocks
function create_memory_object() {
    for (let i = 0; i < memoryMaxNumber; i++) {
        let memoryNumber = i + 1;
        arr_memoryBlocks.push(new Memory(memoryNumber, memorySize[i]));
    }
}

function all_jobs_done() {
    for (let i = 0; i < jobMaxNumber; i++) {
        if (arr_jobs[i].status != "Finished" && arr_jobs[i].status != "Cannot Allocate") {
            return false;
        }
    }
    return true;
}

// If job is finished, remove from memory block
function job_finished(arr_jobs, arr_memoryBlocks) {
    let temp = 0;

    arr_jobs.forEach(job => {
        arr_memoryBlocks.forEach(memory => {
            if (job.timeLeft === 0 && job.status === "Processing") {
                job.status = "Finished";
                job.currentMemoryOccupying = "None";
                temp = job.jobNumber;
            }
            

            for (let i = 0; i < memory.currentJobsOccupying.length; i++) {
              let x = memory.currentJobsOccupying[i];
              if (x === temp) {
                remove_element(memory.currentJobsOccupying, x);
                memory.currentMemorySize = memory.currentMemorySize + job.jobSize;
              }
              
            }
            
        });
    });
}

function remove_element(arr, elementToBeRemoved) {
    let index = arr.indexOf(elementToBeRemoved);

    if (index > -1) {
        arr.splice(index, 1);
    }
    return arr;
}

function _sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}
/**
 * ? ------------------------------------------------------
 * * ----------- DISPLAY TO HTML FUNCTIONS ----------------
 * ? ------------------------------------------------------
 */

// Display to HTML the given job list
function display_given_jobs() {
    html = "";
    arr_jobs.forEach(job => {
        html +=
            "<tr><th>" +
            job.jobNumber +
            "</th><td>" +
            job.time +
            "</td><td>" +
            job.jobSize +
            "</td></tr>";
        document.getElementById("jobs-row").innerHTML = html;
    });
}

// Display to HTML the given memory blocks list
function display_given_memory() {
    html = "";
    arr_memoryBlocks.forEach(memory => {
        html +=
            "<tr><th>" +
            memory.memoryNumber +
            "</th><td>" +
            memory.memorySize +
            "</td></tr>";
        document.getElementById("memory-row").innerHTML = html;
    });
}

// Display to HTML the list of jobs after allocation
function display_jobs_results(arr_jobs) {
    html = "";
    arr_jobs.forEach(job => {
        html +=
            "<tr><th>" +
            job.jobNumber +
            "</th><td>" +
            job.timeLeft +
            "</td><td>" +
            job.jobSize +
            "</td><td>" +
            job.status +
            "</td><td>" +
            job.currentMemoryOccupying +
            "</td></tr>";
        document.getElementById("result-job").innerHTML = html;
    });
}

// Display to HTML the list of memory blocks after allocation
function display_memory_results(arr_memoryBlocks) {
    sort_an_memory_blocks(arr_memoryBlocks);
    html = "";

            arr_memoryBlocks.forEach(memory => {
                html +=
                    "<tr><th>" +
                    memory.memoryNumber +
                    "</th><td>" +
                    memory.memorySize +
                    "</th><td>" +
                    memory.currentMemorySize +
                    "</th><td>" +
                    memory.currentJobsOccupying +
                    "</td></tr>";
                document.getElementById("result-memory").innerHTML = html;
            });
        }
   

// Display to HTML the current allocation method's name
function display_heading(id) {
    html = "";
    if (id === "ff") {
        html = "<h2>First Fit</h2>";
    } else if (id === "bf") {
        html = "<h2>Best Fit</h2>";
    } else if (id === "wf") {
        html = "<h2>Worst Fit</h2>";
    }

    document.getElementById("algo-name").innerHTML = html;
}

// Display waiting queue
function display_waiting_queue(arr_jobs) {
    html = "";

    arr_jobs.forEach(job => {
        if (job.status === "Waiting") {
            html +=
                "<tr><th>" +
                job.jobNumber +
                "</th><td>" +
                job.timeWaiting +
                " second(s)</td></tr>";
            document.getElementById("waiting-queue").innerHTML = html;
            job.timeWaiting++;
        }
        
    });
}

// Show or hide button
function btn_show_list(string) {
    var x = document.getElementById(string);
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

function display_time(time_count) {
    html = time_count;

    document.getElementById("time").innerHTML = html;
}

function display_summarized_data(data, id) {
    html = data;

    document.getElementById(id).innerHTML = html;
}

/**
 * ? ------------------------------------------------------
 * * -------------- SORTING FUNCTIONS ---------------------
 * ? ------------------------------------------------------
 */

// Sort Memory Blocks in ascending order using memory size
function sort_a_memory_blocks(arr_memoryBlocks) {
    arr_memoryBlocks.sort(function (a, b) {
        return a.memorySize - b.memorySize;
    });
}

// Sort Memory Blocks in ascending order using memory number
function sort_an_memory_blocks(arr_memoryBlocks) {
    arr_memoryBlocks.sort(function (a, b) {
        return a.memoryNumber - b.memoryNumber;
    });
}

// Sort Memory Blocks in descending order using memory size
function sort_d_memory_blocks(arr_memoryBlocks) {
    arr_memoryBlocks.sort(function (a, b) {
        return b.memorySize - a.memorySize;
    });
}

/**
 * ? ------------------------------------------------------
 * * ------- MEMORY ALLOCATION ALGORITHMS -----------------
 * ? ------------------------------------------------------
 */

// First Fit allocation method
function first_fit(arr_jobs, arr_memoryBlocks) {
    arr_jobs.forEach(job => {
        if (job.timeLeft != 0 && job.status != "Finished" ) {
            arr_memoryBlocks.forEach(memory => {
                if (
                    memory.currentMemorySize > job.jobSize &&
                    job.status != "Processing" &&
                    job.status != "Finished"
                ) {
                    memory.currentJobsOccupying.push(job.jobNumber);
                    memory.currentMemorySize = memory.currentMemorySize - job.jobSize;

                    job.status = "Processing";
                    job.currentMemoryOccupying = memory.memoryNumber;
                }
                else if (job.jobSize > maxMemoryBlock) {
                    job.status = "Cannot Allocate";
                    job.timeLeft = "---";
                }
              
            });
            
        }
        if (job.status === "Processing") {
            job.timeLeft--;
        }
    });
}

// Best Fit allocation method
function best_fit(arr_jobs, arr_memoryBlocks) {
    sort_a_memory_blocks(arr_memoryBlocks);

    arr_jobs.forEach(job => {
        if (job.timeLeft != 0 && job.status != "Finished" ) {
        arr_memoryBlocks.forEach(memory => {
            if (
                job.jobSize < memory.currentMemorySize &&
                job.status != "Processing" &&
                job.status != "Finished"
            ) {
                memory.currentJobsOccupying.push(job.jobNumber);
                memory.currentMemorySize = memory.currentMemorySize - job.jobSize;

                job.status = "Processing";
                job.currentMemoryOccupying = memory.memoryNumber;
            }
            else if (job.jobSize > maxMemoryBlock) {
                job.status = "Cannot Allocate";
            }
        });
    }
    if (job.status === "Processing") {
        job.timeLeft--;
    }
    });
}

// Worst Fit allocation method
function worst_fit(arr_jobs, arr_memoryBlocks) {
    sort_d_memory_blocks(arr_memoryBlocks);

    arr_jobs.forEach(job => {
        if (job.timeLeft != 0 && job.status != "Finished" ) {
            arr_memoryBlocks.forEach(memory => {
                if (
                    job.jobSize < memory.currentMemorySize &&
                    job.status != "Processing" &&
                    job.status != "Finished"
                ) {
                    memory.currentJobsOccupying.push(job.jobNumber);
                    memory.currentMemorySize = memory.currentMemorySize - job.jobSize;

                    job.status = "Processing";
                    job.currentMemoryOccupying = memory.memoryNumber;
                }
                else if (job.jobSize > maxMemoryBlock) {
                    job.status = "Cannot Allocate";
                }
            });
        }
            if (job.status === "Processing") {
                job.timeLeft--;
            }
    });
}

/**
 * ? ------------------------------------------------------
 * * ----------- TIMELY DATA CALCULATIONS -----------------
 * ? ------------------------------------------------------
 */

// Calculate throughput
function calculate_throughput(arr_jobs) {
    let count = 0;
    arr_jobs.forEach(job => {
        if (job.status === "Processing") {
            count++;
        }
    });
    arr_throughput.push(count);
    return count;
}

// Calculate % partitions not used
function calculate_partitions_not_used(arr_memoryBlocks) {
    let count = 0;
    let res = 0;

    arr_memoryBlocks.forEach(memory => {
        if (memory.currentJobsOccupying.length === 0) {
            count++;
        }
    });

    res = (count / memoryMaxNumber) * 100;
    arr_partitions_not_used.push(res)
    return res;
}

// Calculate % partitions used
function calculate_partitions_used(arr_memoryBlocks) {
    let count = 0;
    let res = 0;

    arr_memoryBlocks.forEach(memory => {
        if (memory.currentJobsOccupying.length > 0) {
            count++;
        }
    });

    res = (count / memoryMaxNumber) * 100;
    arr_partitions_used.push(res);
    return res;
}

// Get total number of jobs waiting
function calculate_waiting_queue(arr_jobs) {
    let count = 0;
    arr_jobs.forEach(job => {
        if (job.status == "Waiting") {
            count++;
        
        }
        if(job.status == "Waiting" && !arr_waiting_queue.includes(job.jobNumber)) {
            arr_waiting_queue.push(job.jobNumber);
           
        }
    });
    return count;
}

// Calculate which memory block has internal fragmentation
function calculate_internal_fragmentation(arr_memoryBlocks) {
    let html = "";
    arr_memoryBlocks.forEach(memory => {
        if (
            memory.currentMemorySize != 0 &&
            memory.currentJobsOccupying.length >= 1
        ) {
            

            html +=
                "<tr><th>" +
                memory.memoryNumber +
                "</th><td>" +
                memory.currentMemorySize +
                "</td></tr>";
            document.getElementById("internal-fragmentation").innerHTML = html;
        }
        //if(!arr_blocks_internal_fragmentation.includes(memory.memoryNumber)) {
            arr_blocks_internal_fragmentation.push(memory.memoryNumber);
        //}
        //if(!arr_blocks_internal_fragmentation_unused_space.includes(memory.currentMemorySize)) {
            arr_blocks_internal_fragmentation_unused_space.push(memory.currentMemorySize);
        //}
    });
}

function calculate_total_time_waiting(arr_jobs) {
    let temp  = 0;

   arr_jobs.forEach(job => {
       temp = job.timeWaiting + temp;
   })

   return temp;
    
}


// Display timely data
function display_timely_data(x, y, z, a) {
    document.getElementById("partitions-not-used").innerHTML = x + " %";
    document.getElementById("partitions-used").innerHTML = y + " %";
    document.getElementById("throughput").innerHTML = z;
    document.getElementById("waiting-queue-length").innerHTML = a;
}

/**
 * ? ------------------------------------------------------
 * * ----------- SUMMARIZED DATA CALCULATIONS -------------
 * ? ------------------------------------------------------
 */

// Calculate average : for throughput, % of partitions used, % of partitions not used
function calculate_average(arr) {
    let temp = 0;
    let arr_size = arr.length;

    for (let i = 0; i < arr.length; i++) {
        temp = arr[i] + temp;
    }

    return temp / arr_size;
}

// Calculate total number : for number of jobs waiting, number of blocks with internal fragmentation
function calculate_total_number(arr) {
    return arr.length;
}

// Calculate sum of data : for total time waiting, total unused space
function calculate_total_data(arr) {
    let temp = 0;

    for (let i = 0; i < arr.length; i++) {
        temp = arr[i] + temp;
    }

    return temp;
}

function calculate_total_time(arr_jobs) {
    let temp = 0;

    arr_jobs.forEach(job => {
        temp = job.time + temp;
    });

    return temp;
}

function run_memory_allocation(algo) {
    if (algo === "First Fit") {
        first_fit(arr_jobs, arr_memoryBlocks);
    } else if (algo === "Best Fit") {
        best_fit(arr_jobs, arr_memoryBlocks);
    } else if (algo === "Worst Fit") {
        worst_fit(arr_jobs, arr_memoryBlocks);
    }

    display_jobs_results(arr_jobs);
    display_memory_results(arr_memoryBlocks);
}

/**
 * ? ------------------------------------------------------
 * ! ----------------- MAIN FUNCTION ----------------------
 * ? ------------------------------------------------------
 */

// Main Function
async function start() {
    let algo = "";
    // Generate jobs and memory blocks
    create_job_object();
    create_memory_object();

    // Display generated result to HTML
    display_given_jobs();
    display_given_memory();

    if (document.getElementById("ff").checked) {
        display_heading("ff");
        algo = "First Fit";
    } else if (document.getElementById("bf").checked) {
        display_heading("bf");
        algo = "Best Fit";
    } else if (document.getElementById("wf").checked) {
        display_heading("wf");
        algo = "Worst Fit";
    }

    // Timely data
    while (!all_jobs_done()) {
        job_finished(arr_jobs, arr_memoryBlocks);
        run_memory_allocation(algo);

        let x = calculate_partitions_not_used(arr_memoryBlocks);
        let y = calculate_partitions_used(arr_memoryBlocks);
        let z = calculate_throughput(arr_jobs);
        let a = calculate_waiting_queue(arr_jobs);

        display_waiting_queue(arr_jobs);
        calculate_internal_fragmentation(arr_memoryBlocks);

        display_timely_data(x, y, z, a);

        time_count++;
        display_time(time_count);
        await _sleep(1000);
    }

    document.getElementById("internal-fragmentation").deleteRow(0);
    document.getElementById("waiting-queue").deleteRow(0);
        // Summarized data
        // Throughput
        let avg_throughput = calculate_average(arr_throughput);
        display_summarized_data(avg_throughput, "avg-throughput");

        // Storage Utilization
        let avg_partitions_not_used = calculate_average(arr_partitions_not_used);
        display_summarized_data(avg_partitions_not_used, "avg-partitions-not-used");

        let avg_partitions_used = calculate_average(arr_partitions_used)
        display_summarized_data(avg_partitions_used, "avg-partitions-used");

        // Waiting Queue
      
        let total_waiting_jobs = calculate_total_number(arr_waiting_queue);
        display_summarized_data(total_waiting_jobs, "total-waiting-jobs");

        let total_time_waiting = calculate_total_time_waiting(arr_jobs);
        display_summarized_data(total_time_waiting, "total-time-waiting-jobs");

        // Internal Fragmentation
        let total_blocks_internal_fragmentation = calculate_total_number(arr_blocks_internal_fragmentation);
        display_summarized_data(total_blocks_internal_fragmentation, "total-blocks-internal-fragmentation");

        let total_unused_space = calculate_total_data(arr_blocks_internal_fragmentation_unused_space);
        display_summarized_data(total_unused_space, "total-unused-space");
console.log(arr_jobs);
        
}
