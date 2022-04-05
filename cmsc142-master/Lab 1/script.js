function selection_sort(params){
    // Outer loop - Loop through the array
    for(let i = 0; i < params.length; i++){
        // Initially set the MIN to first element in array
        let min_index = i;
        // Inner loop - Loop through the next element of i in the array to find the min
        for(let j = i + 1; j < params.length; j++){
            // If min is found, change value of min
            if(params[j] < params[min_index]){
                min_index = j;
            }
        }
        // Swap values of min and i
        [params[i], params[min_index]] = [params[min_index], params[i]];
    }
}

function bubble_sort(params){
    // Outer loop - Loop through the array
    for (let i = 0; i < params.length; i++) {
        // Inner loop - Loop through the array starting at i + 1
        for(let j = i + 1; j < params.length; j++) {
            // If i + 1 is less than i, swap them
            if (params[j] < params[i]) {
                [params[j], params[i]] = [params[i], params[j]];
            }
        }
    }
}

function insertion_sort(params){
    // Outer loop - Loop over the array
    for(let i = 0; i < params.length; i++){
        // Set value that contains the value of i 
        let value = params[i];
        // Let j so outer loop can identify j
        let j;
        for(j = i - 1; j >= 0 && params[j] > value; j--){
            params[j + 1] = params[j];
        }
        params[j + 1] = value;
    }
}

function randomize(max, params) {
    for(let i = 0; i < max; i++) {
        params.push(Math.floor(Math.random() * 101));
    }
}

function copy_array(array) {
    let new_arr = [];
    
    new_arr = Array.from(array);
    return new_arr;
}

function create_avg_case_data(max){
    let avg_data = [];
    randomize(max, avg_data);

    return avg_data;
}

function create_worst_case_data(avg_data){
    let data_worst = [];

    data_worst = Array.from(avg_data);
    data_worst.sort((a, b) => b - a);

    return data_worst;
}

function create_best_case_data(avg_data){
    let data_best = [];

    data_best = Array.from(avg_data);
    data_best.sort((a, b) => a - b);

    return data_best;
}

function create_graph(index, id, selection_sort, bubble_sort, insertion_sort) {
  var data = [
    {
      x: ['Selection Sort', 'Bubble Sort', 'Insertion Sort'],
      y: [selection_sort[index], bubble_sort[index], insertion_sort[index]],
      type: 'bar'
    }
  ];
  
  Plotly.newPlot(id, data);
}

function calculate_selection_sort(avg_case_data, worst_case_data, best_case_data){
    let values = [];

    //console.log("[SS] Data 10: " + avg_case_data);
    var t1 = performance.now();
    selection_sort(avg_case_data);
    var t2 = performance.now();
    let res = t2 - t1;
    //console.log("[SS] Data 10: " + avg_case_data);
    console.log("[SS-AVG] It took " + (res) + ' ms');
    values.push(res);
    
    //console.log("[SS] Data 10 Worst: " + worst_case_data);
    var t3 = performance.now();
    selection_sort(worst_case_data);
    var t4 = performance.now();
    let res2 = t4 - t3;
    //console.log("[SS] Data 10 Worst: " + worst_case_data);
    console.log("[SS-WORST] It took " + (res2) + ' ms');
    values.push(res2);

    //console.log("[SS] Data 10 Best: " + best_case_data);
    var t5 = performance.now();
    selection_sort(best_case_data);
    var t6 = performance.now();
    let res3 = t6 - t5;
    //console.log("[SS] Data 10 Best: " + best_case_data);
    console.log("[SS-BEST] It took " + (res3) + ' ms');
    values.push(res3);
    console.log("==============================================================");

    return values;
}

function calculate_bubble_sort(avg_case_data, worst_case_data, best_case_data){
    let values = [];

    //console.log("[BS] Data 10: " + avg_case_data);
    var t1 = performance.now();
    bubble_sort(avg_case_data);
    var t2 = performance.now();
    let res = t2 - t1;
    //console.log("[BS] Data 10: " + avg_case_data);
    console.log("[BS-AVG] It took " + (res) + ' ms');
    values.push(res);

    //console.log("[BS] Data 10 Worst: " + worst_case_data);
    var t3 = performance.now();
    bubble_sort(worst_case_data);
    var t4 = performance.now();
    let res2 = t4 - t3;
    //console.log("[BS] Data 10 Worst: " + worst_case_data);
    console.log("[BS-WORST] It took " + (res2) + ' ms');
    values.push(res2);

    //console.log("[BS] Data 10 Best: " + best_case_data);
    var t5 = performance.now();
    bubble_sort(best_case_data);
    var t6 = performance.now();
    let res3 = t6 - t5;
    //console.log("[BS] Data 10 Best: " + best_case_data);
    console.log("[BS-BEST] It took " + (res3) + ' ms');
    values.push(res3);
    console.log("==============================================================");

    return values;
}

function calculate_insertion_sort(avg_case_data, worst_case_data, best_case_data){
    let values = [];

    //console.log("[IS] Data 10: " + avg_case_data);
    var t1 = performance.now();
    insertion_sort(avg_case_data);
    var t2 = performance.now();
    let res = t2 - t1;
    //console.log("[IS] Data 10: " + avg_case_data);
    console.log("[IS-AVG] It took " + (res) + ' ms');
    values.push(res);

    //console.log("[IS] Data 10 Worst: " + worst_case_data);
    var t3 = performance.now();
    insertion_sort(worst_case_data);
    var t4 = performance.now();
    let res2 = t4 - t3;
    //console.log("[IS] Data 10 Worst: " + worst_case_data);
    console.log("[IS-WORST] It took " + (res2) + ' ms');
    values.push(res2);

    //console.log("[IS] Data 10 Best: " + best_case_data);
    var t5 = performance.now();
    insertion_sort(best_case_data);
    var t6 = performance.now();
    let res3 = t6 - t5;
    //console.log("[IS] Data 10 Best: " + best_case_data);
    console.log("[IS-BEST] It took " + (res3) + ' ms');
    values.push(res3);

    return values;
}

function run_data10(){
    const avg_case_data = create_avg_case_data(10);
    const worst_case_data = create_worst_case_data(avg_case_data);
    const best_case_data = create_best_case_data(avg_case_data);
    
    const avg_case_copy = copy_array(avg_case_data);
    const worst_case_copy = copy_array(worst_case_data);
    const best_case_copy = copy_array(best_case_data);

    const avg_case_copy2 = copy_array(avg_case_data);
    const worst_case_copy2 = copy_array(worst_case_data);
    const best_case_copy2 = copy_array(best_case_data);

    // Display array contents
    console.log(avg_case_data);
    console.log(worst_case_data);
    console.log(best_case_data);
    
    console.log("=== DATA MAX: 10 ===");
    let selection_sort = calculate_selection_sort(avg_case_data, worst_case_data, best_case_data);
    let bubble_sort = calculate_bubble_sort(avg_case_copy, worst_case_copy, best_case_copy);
    let insertion_sort = calculate_insertion_sort(avg_case_copy2, worst_case_copy2, best_case_copy2);

    create_graph(0, '10-avg', selection_sort, bubble_sort, insertion_sort);
    create_graph(1, '10-worst', selection_sort, bubble_sort, insertion_sort);
    create_graph(2, '10-best', selection_sort, bubble_sort, insertion_sort);
}

function run_data100(){
    const avg_case_data = create_avg_case_data(100);
    const worst_case_data = create_worst_case_data(avg_case_data);
    const best_case_data = create_best_case_data(avg_case_data);
    
    const avg_case_copy = copy_array(avg_case_data);
    const worst_case_copy = copy_array(worst_case_data);
    const best_case_copy = copy_array(best_case_data);

    const avg_case_copy2 = copy_array(avg_case_data);
    const worst_case_copy2 = copy_array(worst_case_data);
    const best_case_copy2 = copy_array(best_case_data);

    // Display array contents
    console.log(avg_case_data);
    console.log(worst_case_data);
    console.log(best_case_data);
    
    console.log("=== DATA MAX: 100 ===");
    let selection_sort = calculate_selection_sort(avg_case_data, worst_case_data, best_case_data);
    let bubble_sort = calculate_bubble_sort(avg_case_copy, worst_case_copy, best_case_copy);
    let insertion_sort = calculate_insertion_sort(avg_case_copy2, worst_case_copy2, best_case_copy2);

    create_graph(0, '100-avg', selection_sort, bubble_sort, insertion_sort);
    create_graph(1, '100-worst', selection_sort, bubble_sort, insertion_sort);
    create_graph(2, '100-best', selection_sort, bubble_sort, insertion_sort);
}

function run_data1000(){
    const avg_case_data = create_avg_case_data(1000);
    const worst_case_data = create_worst_case_data(avg_case_data);
    const best_case_data = create_best_case_data(avg_case_data);
    
    const avg_case_copy = copy_array(avg_case_data);
    const worst_case_copy = copy_array(worst_case_data);
    const best_case_copy = copy_array(best_case_data);

    const avg_case_copy2 = copy_array(avg_case_data);
    const worst_case_copy2 = copy_array(worst_case_data);
    const best_case_copy2 = copy_array(best_case_data);

    // Display array contents
    console.log(avg_case_data);
    console.log(worst_case_data);
    console.log(best_case_data);
    
    console.log("=== DATA MAX: 1000 ===");
    let selection_sort = calculate_selection_sort(avg_case_data, worst_case_data, best_case_data);
    let bubble_sort = calculate_bubble_sort(avg_case_copy, worst_case_copy, best_case_copy);
    let insertion_sort = calculate_insertion_sort(avg_case_copy2, worst_case_copy2, best_case_copy2);

    create_graph(0, '1000-avg', selection_sort, bubble_sort, insertion_sort);
    create_graph(1, '1000-worst', selection_sort, bubble_sort, insertion_sort);
    create_graph(2, '1000-best', selection_sort, bubble_sort, insertion_sort);
}


run_data10();
run_data100();
run_data1000();