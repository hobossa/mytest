# coding=utf8
import sys


def fact(x):
    if x == 1:
        return 1
    else:
        return x * fact(x - 1)


def run():
    print(fact(5))


if __name__ == "__main__":
    if sys.version_info[0] < 3:
        raise Exception("Python 3 or a more recent version is required.")
    run()
