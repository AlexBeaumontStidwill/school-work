import csv
import math
import matplotlib.pyplot
import plotly.plotly
from plotly.graph_objs import Scatter, Layout
import numpy

def distance(x, y):
    return math.fabs(score(x) - y)

def score(x):
    # calculate the hamming weight
    return sum(c1 == '1' for c1 in x)

def arrayInit(filepath):
    list = []
    with open(filepath) as f:
        reader = csv.reader(f)
        for row in reader:
            list.append(row)
    return numpy.rot90(list)

def list(dict):
    list = []
    for key in dict:
        list.append(key)
    return key

def coordsInit(coords, which):
    list = []
    for key in which:
        list.append(coords[key])
    print (list)
    return list

def images(k, dataset):
    print("Analyzing: ", dataset)
    goodImages = {}
    avgScore = 0 # Current average weight of photos
    with open(dataset) as f:
        reader = csv.reader(f) # store the csv file
        x = 1
        z = 1
        q = 0
        for row in reader: # loop through all data
            y = ''.join(row) # Turn into single 10 digit string
            if x <= k - round(k / 5): # fill the list with the first k/5 values
                goodImages[x] = y
                if x < 10: # average weight for first 10 images
                    avgScore += score(y)
                else:
                    avgScore = ((avgScore / 10) * len(goodImages) + score(y)) / (len(goodImages) + 1) # not exact average
            if x <= k: 
                # fill the list with the remaining k values, and make them different than the average
                if distance(y, 1) < 5:
                    goodImages[x] = y
                if distance(y, 1) > 6:
                    goodImages[x] = y    
            else:
                if distance(y, z) > avgScore: # make sure the photo is different enough
                   # continuously change the value of z
                   if z != 9:
                        z += 1
                   else : 
                        z = 1
                   for key in goodImages:
                        if(score(goodImages[key]) == round(avgScore)):
                            del goodImages[key]
                            goodImages[x] = y
                            # calculate new average weight
                            avgScore = (avgScore * len(goodImages) + score(y))/(len(goodImages) + 1)
                            break
            x += 1

    return goodImages

dict = images(150, 'images.csv')

print (dict)

coords = arrayInit('coords.csv')

plotly.offline.plot({
    "data": [Scatter(x=coordsInit(coords[1], dict), y = coordsInit(coords[0], dict))],
    "layout": Layout(title = "Plot of UAV Problem")
    })