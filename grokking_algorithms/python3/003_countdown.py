# coding=utf8
import sys

def countdown(i):
    # base case
    print(i)
    if i <=0 :
        return
    # recursive case
    else :
        countdown(i-1)


def run():
    countdown(5)


if __name__ == "__main__":
    if sys.version_info[0] < 3:
        raise Exception("Python 3 or a more recent version is required.")
    run()