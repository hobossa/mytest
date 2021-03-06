# coding=utf-8
import sys


def binarySearch(list, item):
    # O(log n)
    low = 0
    high = len(list) - 1

    while low <= high:
        mid = (low + high) // 2
        guess = list[mid]
        if guess == item:
            return mid
        if guess > item:
            high = mid - 1
        else:
            low = mid + 1
    return None


def run():
    my_list = [1, 3, 5, 7, 9]
    print(binarySearch(my_list, 3))  # => 1
    print(binarySearch(my_list, -1))  # => None


if __name__ == "__main__":
    if sys.version_info[0] < 3:
        raise Exception("Python 3 or a more recent version is required.")
    run()
