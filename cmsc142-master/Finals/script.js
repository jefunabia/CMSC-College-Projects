/* 
? References: 
* https://youtu.be/nLmhmB6NzcM
*
* https://gist.github.com/lqt0223/21f033450a9d762ce8aee4da336363b1
*
*/

// DP approach
function knapsack(items, capacity) {
  // Variable 'memo' is a grid(2-dimentional array) to store optimal solution for sub-problems, which will be later used as the code execution goes on.
  // This is called memoization in programming.
  // The cell will store best solution objects for different capacities and selectable items.
  var memo = [];

  // Filling the sub-problem solutions grid
  for (var i = 0; i < items.length; i++) {
    // Variable 'cap' is the capacity for sub-problems.
    var row = [];
    for (var cap = 1; cap <= capacity; cap++) {
      row.push(getSolution(i, cap));
    }
    memo.push(row);
  }

  // The right-bottom-corner cell of the grid contains the final solution for the whole problem.
  return getLast();

  function getLast() {
    var lastRow = memo[memo.length - 1];
    return lastRow[lastRow.length - 1];
  }

  function getSolution(row, cap) {
    const NO_SOLUTION = { maxValue: 0, subset: [] };
    // the column number starts from zero.
    var col = cap - 1;
    var lastItem = items[row];
    // The remaining capacity for the sub-problem to solve.
    var remaining = cap - lastItem.w;

    // Refer to the last solution for this capacity,
    // which is in the cell of the previous row with the same column
    var lastSolution =
      row > 0 ? memo[row - 1][col] || NO_SOLUTION : NO_SOLUTION;
    // Refer to the last solution for the remaining capacity,
    // which is in the cell of the previous row with the corresponding column
    var lastSubSolution =
      row > 0 ? memo[row - 1][remaining - 1] || NO_SOLUTION : NO_SOLUTION;

    // If any one of the items weights greater than the 'cap', return the last solution
    if (remaining < 0) {
      return lastSolution;
    }

    // Compare the current best solution for the sub-problem with a specific capacity
    // to a new solution trial with the lastItem(new item) added
    var lastValue = lastSolution.maxValue;
    var lastSubValue = lastSubSolution.maxValue;

    var newValue = lastSubValue + lastItem.v;
    if (newValue >= lastValue) {
      // copy the subset of the last sub-problem solution
      var _lastSubSet = lastSubSolution.subset.slice();
      _lastSubSet.push(lastItem);
      return { maxValue: newValue, subset: _lastSubSet };
    } else {
      return lastSolution;
    }
  }
}

// test 1
var items = [
  { w: 1, v: 1 },
  { w: 2, v: 4 },
  { w: 3, v: 6 }
];

var capacity = 3;

// Test 2
var items2 = [
  { w: 3, v: 25 },
  { w: 2, v: 20 },
  { w: 1, v: 15 },
  { w: 4, v: 40 },
  { w: 5, v: 50 }
];

var capacity2 = 6;

function display_to_HTML(result, div) {
  html = "";
  temp = "";
  html = "<h5>Max Value:</h5><p> " + result.maxValue;

  for (let i = 0; i < result.subset.length; i++) {
    temp =
      "<p>Weight: " +
      result.subset[i].w +
      "</p>" +
      "<p>Value: " +
      result.subset[i].v +
      "</p><hr>";
    document.getElementById(div).insertAdjacentHTML("afterend", temp);
  }

  document.getElementById(div).innerHTML = html;
}
console.log(knapsack(items, capacity));
console.log(knapsack(items2, capacity2));

let res = knapsack(items, capacity);
let res2 = knapsack(items2, capacity2);
display_to_HTML(res, "test-case-1");
display_to_HTML(res2, "test-case-2");
