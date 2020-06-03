# coding=utf-8
import sys

# the graph
graph = {}
graph["start"] = {}
graph["start"]["a"] = 6
graph["start"]["b"] = 2

graph["a"] = {}
graph["a"]["fin"] = 1

graph["b"] = {}
graph["b"]["a"] = 3
graph["b"]["fin"] = 5

graph["fin"] = {}

# the costs table
infinity = float("inf")
costs = {}
costs["a"] = 6
costs["b"] = 2
costs["fin"] = infinity

# the parents table
parents = {}
parents["a"] = "start"
parents["b"] = "start"
parents["fin"] = None

processed = []


def find_lowest_cost_node(costs):
    lowest_cost = float("inf")
    lowest_cost_node = None
    # Go through each node.
    for node in costs:
        cost = costs[node]
        # If it's the lowest cost so far and hasn't been processed yet...
        if cost < lowest_cost and node not in processed:
            # ... set it as the new lowest-cost node.
            lowest_cost = cost
            lowest_cost_node = node
    return lowest_cost_node


def dijkstras(costs):
    # Find the lowest-cost node that you haven't processed yet.
    node = find_lowest_cost_node(costs)
    # If you've processed all the nodes, this while loop is done.
    while node is not None:
        cost = costs[node]
        # Go through all the neighbors of this node.
        neighbours = graph[node]
        for n in neighbours.keys():
            new_cost = cost + neighbours[n]
            # If it's cheaper to get to this neighbor by going through this node...
            if costs[n] > new_cost:
                # update the cost for this node
                costs[n] = new_cost
                # update parent
                parents[n] = node
        # Mark the node as processed.
        processed.append(node)
        # Find the nex node to process, and loop.
        node = find_lowest_cost_node(costs)


def run():
    dijkstras(costs)
    print("Cost from the start to each node:")
    print(costs)


if __name__ == "__main__":
    if sys.version_info[0] < 3:
        raise Exception("Python 3 or a more recent version is required.")
    run()
