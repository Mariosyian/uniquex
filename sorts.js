/* eslint-disable require-jsdoc */
const equals = (a, b, invert) => {
  if (invert) {
    if (a[1] < b[1]) {
      return -1;
    } else if (a[1] > b[1]) {
      return 1;
    }
  } else {
    if (a[1] < b[1]) {
      return 1;
    } else if (a[1] > b[1]) {
      return -1;
    }
  }

  return 0;
};

module.exports = {
  bubbleSort: (list) => {
    for (i = 0; i < list.length; i++) {
      for (j = 0; j < ( list.length - i -1 ); j++) {
        if (equals(list[j], list[j+1]) === -1) {
          const temp = list[j];
          list[j] = list[j + 1];
          list[j+1] = temp;
        }
      }
    }

    return list;
  },
  heapSort: (list) => {
    function sort(list) {
      const N = list.length;

      for (i = Math.floor(N / 2) - 1; i >= 0; i--) {
        heapify(list, N, i);
      }

      for (i = N - 1; i > 0; i--) {
        const temp = list[0];
        list[0] = list[i];
        list[i] = temp;

        heapify(list, i, 0);
      }

      return list;
    }

    function heapify(list, N, i) {
      let largest = i;
      const left = 2 * i + 1;
      const right = 2 * i + 2;

      if (left < N && equals(list[left], list[largest]) === -1) {
        largest = left;
      }

      if (right < N && equals(list[right], list[largest]) === -1) {
        largest = right;
      }

      if (largest != i) {
        const swap = list[i];
        list[i] = list[largest];
        list[largest] = swap;

        heapify(list, N, largest);
      }
    }

    return sort(list);
  },
  mergeSort: (list, left, right) => {
    function merge(list, left, mid, right) {
      const n1 = mid - left + 1;
      const n2 = right - mid;

      const L = new Array(n1);
      const R = new Array(n2);

      for (i = 0; i < n1; i++) {
        L[i] = list[left + i];
      }
      for (j = 0; j < n2; j++) {
        R[j] = list[mid + 1 + j];
      }

      let i = 0;
      let j = 0;
      let k = left;

      while (equals([0, i], n1) === 1 && equals([0, j], n2) === 1) {
        if (L[i] <= R[j]) {
          list[k] = L[i];
          i++;
        } else {
          list[k] = R[j];
          j++;
        }
        k++;
      }

      while (equals([0, i], n1) === 1) {
        list[k] = L[i];
        i++;
        k++;
      }

      while (equals([0, j], n2) === 1) {
        list[k] = R[j];
        j++;
        k++;
      }

      return list;
    }

    function mSort(list, left, right) {
      if (left >= right) {
        return list;
      }
      const mid = left + parseInt((right-left)/2);
      list = mSort(list, left, mid);
      list = mSort(list, mid+1, right);
      list = merge(list, left, mid, right);
    }

    return mSort(list, left, right);
  },
};
