class Process {
    constructor(processNumber, arrivalTime, burstTime, priority) {
        this.processNumber = processNumber;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.isFinished = false;
    }

}

const quizMaxProcesses = 6;
const quizArrivalTime = [0, 1, 2, 3, 4, 5];
const quizBurstTime = [10, 15, 8, 7, 9, 8];
const quizPriorty = [3, 5, 1, 2, 4, 6];

const datasetMaxProcesses = 20;
const ds1ArrivalTime = [0, 1, 2, 3, 4, 4, 5, 5, 6, 7, 8, 9, 9, 10, 10, 11, 11, 12, 13, 13];
const ds1BurstTime = [20, 15, 11, 9, 11, 9, 12, 14, 15, 19, 25, 21, 8, 3, 4, 14, 12, 10, 10, 9];
const ds1Priorty = [0, 0, 1, 1, 2, 3, 2, 4, 3, 2, 0, 1, 2, 5, 5, 4, 4, 2, 3, 2];

const ds2ArrivalTime = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19];
const ds2BurstTime = [10, 3, 15, 24, 6, 7, 25, 14, 13, 9, 7, 8, 5, 4, 1, 11, 16, 17, 18, 20];
const ds2Priorty = [5, 1, 0, 4, 2, 0, 4, 1, 2, 3, 4, 3, 2, 1, 0, 4, 5, 5, 4, 3];

let processes = [];
let quantum = "None";

function load_quiz_dataset() {
    for (let i = 0; i < quizMaxProcesses; i++) {
        let processNumber = i + 1;

        processes.push(new Process(processNumber, quizArrivalTime[i], quizBurstTime[i], quizPriorty[i]));
    }
}

function load_ds1() {
    for (let i = 0; i < datasetMaxProcesses; i++) {
        let processNumber = i + 1;

        processes.push(new Process(processNumber, ds1ArrivalTime[i], ds1BurstTime[i], ds1Priorty[i]));
    }
}

function load_ds2() {
    for (let i = 0; i < datasetMaxProcesses; i++) {
        let processNumber = i + 1;

        processes.push(new Process(processNumber, ds2ArrivalTime[i], ds2BurstTime[i], ds2Priorty[i]));
    }
}

function display_dataset() {
    html = "";
    processes.forEach(process => {
        html += "<tr><th>" + process.processNumber + "</th><td>" + process.arrivalTime + "</td><td>" + process.burstTime + "</td><td>" + process.priority + "</td></tr>";
        document.getElementById('main').innerHTML = html;
    })
}

function calculate_waiting_time(processes) {
    let waiting_time = [];
    waiting_time[0] = 0;

    for (let i = 1; i < processes.length; i++) {
        waiting_time[i] = processes[i - 1].burstTime + waiting_time[i - 1];
    }

    return waiting_time;
}

function calculate_turnaround_time(waiting_time) {
    let turnaround_time = [];
    console.log("WT" + waiting_time);
    
    for (let i = 0; i < processes.length; i++) {
        turnaround_time[i] = processes[i].burstTime + waiting_time[i];
    }

    return turnaround_time;
}

function calculate_turnaround_time_srpt(waiting_time) {
    let turnaround_time = [];
    console.log("WT" + waiting_time);
    
    for (let i = 0; i < processes.length; i++) {
        turnaround_time[i] = processes[i].burstTime + waiting_time[i] + processes[i].arrivalTime;
    }

    return turnaround_time;
}

function calculate_average_waiting_time(waiting_time) {
    let total_waiting_time = 0;
    let result = 0;

    for (let i = 0; i < waiting_time.length; i++) {
        total_waiting_time = total_waiting_time + waiting_time[i];
    }

    result = total_waiting_time / waiting_time.length;

    return result;
}

function calculate_average_turnaround_time(turnaround_time) {
    let total_turnaround_time = 0;
    let result = 0;

    for (let i = 0; i < turnaround_time.length; i++) {
        total_turnaround_time = total_turnaround_time + turnaround_time[i];
    }

    result = total_turnaround_time / turnaround_time.length;

    return result;
}

function calculate_waiting_time_rr(processes, quantum) {
    let wt = [];
    wt[0] = 0;

    console.log(processes);
    let rem_bt = [];
    for (let i = 0; i < processes.length; i++)
        rem_bt[i] = processes[i].burstTime;

    let t = 0; // Current time 

    // Keep traversing processes in round robin manner 
    // until all of them are not done. 
    while (true) {
        let done = true;

        // Traverse all processes one by one repeatedly 
        for (let j = 0; j < processes.length; j++) {
            // If burst time of a process is greater than 0 
            // then only need to process further 
            if (rem_bt[j] > 0) {
                done = false; // There is a pending process 

                if (rem_bt[j] > quantum) {
                    // Increase the value of t i.e. shows 
                    // how much time a process has been processed 
                    t += quantum;

                    // Decrease the burst_time of current process 
                    // by quantum 
                    rem_bt[j] -= quantum;
                }

                // If burst time is smaller than or equal to 
                // quantum. Last cycle for this process 
                else {
                    // Increase the value of t i.e. shows 
                    // how much time a process has been processed 
                    t = t + rem_bt[j];

                    // Waiting time is current time minus time 
                    // used by this process 
                    wt[j] = t - processes[j].burstTime;

                    // As the process gets fully executed 
                    // make its remaining burst time = 0 
                    rem_bt[j] = 0;
                }
            }
        }

        // If all processes are done 
        if (done == true)
            break;
    }

    return wt;
}

function calculate_waiting_time_srpt(processes) {
    let rt = []; 
    let wt = [];

    // Copy the burst time into rt[] 
    for (let i = 0; i < processes.length; i++) 
        rt[i] = processes[i].burstTime; 
   
    let complete = 0;
    let t = 0;
    let minm = Number.MAX_VALUE; 
    let shortest = 0;
    let finish_time; 
    let check = false; 
   
    // Process until all processes gets 
    // completed 
    while (complete != processes.length) { 
   
        // Find process with minimum 
        // remaining time among the 
        // processes that arrives till the 
        // current time` 
        for (let j = 0; j < processes.length; j++)  
        { 
            if ((processes[j].arrivalTime <= t) && 
              (rt[j] < minm) && rt[j] > 0) { 
                minm = rt[j]; 
                shortest = j; 
                check = true; 
            } 
        } 
   
        if (check == false) { 
            t++; 
            continue; 
        } 
   
        // Reduce remaining time by one 
        rt[shortest]--; 
   
        // Update minimum 
        minm = rt[shortest]; 
        if (minm == 0) 
            minm = Number.MAX_VALUE; 
   
        // If a process gets completely 
        // executed 
        if (rt[shortest] == 0) { 
   
            // Increment complete 
            complete++; 
            check = false; 
   
            // Find finish time of current 
            // process 
            finish_time = t + 1; 
   
            // Calculate waiting time 
            wt[shortest] = finish_time - 
                         processes[shortest].burstTime - 
                         processes[shortest].arrivalTime; 
   
            if (wt[shortest] < 0) 
                wt[shortest] = 0; 
        } 
        // Increment time 
        t++; 
    } 

    return wt;
}

function display_results(waiting_time, turnaround_time, avg_waiting_time, avg_turnaround_time, processes, quantum) {

    html = "";
    let i;
    for (i = 0; i < waiting_time.length; i++) {
        html += "<tr><th>" + processes[i].processNumber + "</th><td>" + waiting_time[i]; + "</td></tr>";
    }

    document.getElementById('result').innerHTML = html;

    tbl = document.getElementById("result");
    tr = tbl.getElementsByTagName("tr");

    for (i = 0; i < tr.length; i++) {
        let td = document.createElement('td');
        let input = document.createElement('P');
        input.innerHTML = turnaround_time[i];
        td.appendChild(input);
        tr[i].appendChild(td);
    }

    document.getElementById('avg').innerHTML = avg_waiting_time + " ms";
    document.getElementById('tat').innerHTML = avg_turnaround_time + " ms";document.getElementById('quantum').innerHTML = quantum;
}

function reset(){
    window.location.reload();
}

function fcfs() {
    let waiting_time = calculate_waiting_time(processes);
    let turnaround_time = calculate_turnaround_time(waiting_time);
    let avg_waiting_time = calculate_average_waiting_time(waiting_time);
    let avg_turnaround_time = calculate_average_turnaround_time(turnaround_time);

    display_results(waiting_time, turnaround_time, avg_waiting_time, avg_turnaround_time, processes, quantum);
}

function sjf() {

    processes.sort((a, b) => (a.burstTime > b.burstTime) ? 1 : (a.burstTime === b.burstTime) ? ((a.processNumber > b.processNumber) ? 1 : -1) : -1);

    console.log(processes);

    let waiting_time = calculate_waiting_time(processes);
    let turnaround_time = calculate_turnaround_time(waiting_time);
    let avg_waiting_time = calculate_average_waiting_time(waiting_time);
    let avg_turnaround_time = calculate_average_turnaround_time(turnaround_time);

    display_results(waiting_time, turnaround_time, avg_waiting_time, avg_turnaround_time, processes, quantum);
}

function priority() {
    processes.sort((a, b) => (a.priority > b.priority) ? 1 : (a.priority === b.priority) ? ((a.arrivalTime > b.arrivalTime) ? 1 : -1) : -1);

    console.log(processes);

    let waiting_time = calculate_waiting_time(processes);
    let turnaround_time = calculate_turnaround_time(waiting_time);
    let avg_waiting_time = calculate_average_waiting_time(waiting_time);
    let avg_turnaround_time = calculate_average_turnaround_time(turnaround_time);

    display_results(waiting_time, turnaround_time, avg_waiting_time, avg_turnaround_time, processes, quantum);
}

function round_robin(quantum) {
    let waiting_time = calculate_waiting_time_rr(processes, quantum);
    let turnaround_time = calculate_turnaround_time(waiting_time);
    let avg_waiting_time = calculate_average_waiting_time(waiting_time);
    let avg_turnaround_time = calculate_average_turnaround_time(turnaround_time);

    display_results(waiting_time, turnaround_time, avg_waiting_time, avg_turnaround_time, processes, quantum);
}

function srpt(){
    let waiting_time = calculate_waiting_time_srpt(processes);
    let turnaround_time = calculate_turnaround_time_srpt(waiting_time);
    let avg_waiting_time = calculate_average_waiting_time(waiting_time);
    let avg_turnaround_time = calculate_average_turnaround_time(turnaround_time);

    display_results(waiting_time, turnaround_time, avg_waiting_time, avg_turnaround_time, processes, quantum);
}

function start() {
    if (document.getElementById('quiz').checked) {
        load_quiz_dataset();
        display_dataset();
    }
    else if (document.getElementById('ds1').checked) {
        load_ds1();
        display_dataset();
    }
    else if (document.getElementById('ds2').checked) {
        load_ds2();
        display_dataset();
    }

    if (document.getElementById('fcfs').checked) {
        fcfs();
    }
    else if (document.getElementById('sjf').checked) {
        sjf();
    }
    else if (document.getElementById('priority').checked) {
        priority();
    }
    else if (document.getElementById('rr').checked && document.getElementById('quiz').checked) {
        round_robin(3);
    }
    else if(document.getElementById('rr').checked && (document.getElementById('ds1').checked || document.getElementById('ds2').checked)) {
        round_robin(4);
    }
    else if (document.getElementById('srpt').checked) {
        srpt();
    }
}