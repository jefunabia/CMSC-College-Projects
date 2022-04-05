function fib_iteration(n) {
    let arr = [0, 1];
    for (let i = 2; i <= n; i++) {
        arr.push(arr[i - 2] + arr[i - 1]);
    }
    return arr[n];
}

function fib_recursion(n) {
    if (n <= 1) {
        return n;
    }
    return fib_recursion(n - 2) + fib_recursion(n - 1);
}

function fib_recursion_with_memoization(n, memo) {
    // If memo has a value, assign it to itself, else default {}
    memo = memo || {};

    if (memo[n]){
        return memo[n];
    } 
    if (n <= 1){
        return n;
    } 

    return memo[n] = fib_recursion_with_memoization(n - 1, memo) + fib_recursion_with_memoization(n - 2, memo);
}

function calculate_fib_recursion(n){
    let t1 = 0;
    let t2 = 0;
    let res = 0;

    t1 = performance.now();
    fib_recursion(n);
    t2 = performance.now();
    res = t2 - t1;
    
    return res;
}

function calculate_fib_recursion_with_memo(n){
    let t1 = 0;
    let t2 = 0;
    let res = 0;

    t1 = performance.now();
    fib_recursion_with_memoization(n);
    t2 = performance.now();
    res = t2 - t1;
    
    return res;
}

function calculate_fib_iteration(n){
    let t1 = 0;
    let t2 = 0;
    let res = 0;

    t1 = performance.now();
    fib_iteration(n);
    t2 = performance.now();
    res = t2 - t1;
    
    return res;
}

function start(function_name){
    let input_array = [10, 25, 40];
    let res_array = [];

    let res = function_name(input_array[0]);
    let res2 = function_name(input_array[1]);
    let res3 = function_name(input_array[2]);
    
    res_array.push(res);
    res_array.push(res2);
    res_array.push(res3);

    return res_array;
}

function graph(fib_recursion_arr, fib_recursion_memo_arr, fib_iteration_arr){

    var trace1 = {
        x: [10, 25, 40],
        y: [fib_recursion_arr[0], fib_recursion_arr[1], fib_recursion_arr[2]],
        type: 'scatter',
        name: "Fibonacci Series using Recursion"
      };

      var trace2 = {
        x: [10, 25, 40],
        y: [fib_recursion_memo_arr[0], fib_recursion_memo_arr[1], fib_recursion_memo_arr[2]],
        type: 'scatter',
        name: "Fibonacci Series using Recursion w/ Memoization"
      };

      var trace3 = {
        x: [10, 25, 40],
        y: [fib_iteration_arr[0], fib_iteration_arr[1], fib_iteration_arr[2]],
        type: 'scatter',
        name: "Fibonacci Series using Iterative Method"
      };
      
      var data = [trace1, trace2, trace3];
      
      var layout = {
        xaxis: {
          title: {
            text: 'Number of inputs',
          },
        },
        yaxis: {
          title: {
            text: 'Time (ms)',
          }
        }
      };

      Plotly.newPlot("fib", data, layout);
}

function max(a, b){
    return a > b ? a : b;
}

// https://www.youtube.com/watch?v=sSno9rV8Rhg&
function lcs(string1, string2) {
    let string1_length = string1.length;
    let string2_length = string2.length;
    let arr = [];
    let i = 0;
    let j = 0;

    // Add 0 initially to row & col
    for(i = 0; i <= string1_length; i++) {
        arr.push([0]);
    }
    for(j = 0; j <= string2_length; j++){
        arr[0].push(0);
    }

    console.log(arr);
    
    // Following the pseudocode
     for(i = 0; i < string1_length; i++) {
        for(j = 0; j < string2_length; j++){
            if(string1[i] === string2[j]){
                arr[i + 1][j + 1] = 1 + arr[i][j];
            }
            else{
                arr[i + 1][j + 1] = max(arr[i + 1][j], arr[i][j + 1]);
            }
        }
    }

    // For the sequence
    return(
        function backtrack(i, j) {
        if (i * j === 0) {
            return "";
        }

        if (string1[i - 1] === string2[j - 1]) {
            return backtrack(i - 1, j - 1) + string1[i - 1];
        }

        if(arr[i][j - 1] > arr[i - 1][j]){
            return backtrack(i, j - 1);
        }else{
            return backtrack(i - 1, j);
        }
    }(string1_length, string2_length));
}

function submitLCS(){
    let s1 = document.getElementById('s1').value;
    let s2 = document.getElementById('s2').value;
    let res = lcs(s1,s2);

    document.getElementById('res').innerHTML = res;
}

function main(){
    let x = start(calculate_fib_recursion);
    let y = start(calculate_fib_recursion_with_memo);
    let z = start(calculate_fib_iteration);

    graph(x, y, z);
}

main();