# coding=utf-8
import sys


def subsequence(strA, strB):
    #    s t r B
    #   +-+-+-+-+
    # s |1 1 1 1
    # t |1 2 2 2
    # r |1 2 3 3
    # A |1 2 3 3
    row = len(strA)
    col = len(strB)
    value_matrix = [[0 for j in range(col)] for i in range(row)]
    max_value = 0
    max_value_coordinates = []
    for i in range(row):
        for j in range(col):
            if strA[i] == strB[j]:
                if i == 0 or j == 0:
                    value_matrix[i][j] = 1
                else:
                    value_matrix[i][j] = value_matrix[i - 1][j - 1] + 1
                if max_value < value_matrix[i][j]:
                    max_value = value_matrix[i][j]
                    max_value_coordinates = [(i, j)]
                elif max_value == value_matrix[i][j]:
                    max_value_coordinates.append((i, j))
            else:
                if i == 0 and j == 0:
                    value_matrix[i][j] = 0
                elif i == 0 and j > 0:
                    value_matrix[i][j] = value_matrix[i][j - 1]
                elif i > 0 and j == 0:
                    value_matrix[i][j] = value_matrix[i - 1][j]
                else:
                    value_matrix[i][j] = max(value_matrix[i - 1][j],
                                             value_matrix[i][j - 1])

    for v in value_matrix:
        print(v)

    # toDo: we can get max sequences with max_value_coordinates


def run():
    subsequence("fishf", "pfoshe")


if __name__ == "__main__":
    if sys.version_info[0] < 3:
        raise Exception("Python 3 or a more recent version is required.")
    run()
