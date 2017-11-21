#Sort the given points into increasing order of x coordinate, and of y coordinate;
#Compute the bounding box of the complete set of points.
#In a binary-search style, divide the bounding rectangle by a vertical line so that one or other of the 'halves' has many points per unit area.
#Now, using that rectangle as the new bounding box, divide it using a horizontal line so that one of the halves has many points per unit area.
#Continue in this way alternating horizontal and vertical separations, stopping when neither of the subrectangles is better than its parent.
#Return the fullest subrectangle, i.e. the coordinates for its corners.

import csv
import numpy

xcoordinates = []
ycoordinates = []

def loadArray(dataset):
    list = []
    with open(dataset) as f:
        reader = csv.reader(f)
        for row in reader:
            list2 = numpy.array(row)
            list.append(list2.astype(float))
    return(list)

def convexHull(array, density):
    dens = []
    xcoordinates = sorted(array, key = lambda x: x[0])
    ycoordinates = sorted(array, key = lambda x: x[1])
    
    half = len(array)//2
    xleft = xcoordinates[:half]
    xright = xcoordinates[half:]
    ytop = ycoordinates[:half]
    ybottom = ycoordinates[half:]

    dens1 = boundingBox(xleft)['density']
    x1 = boundingBox(xleft)['xmin']
    x2 = boundingBox(xleft)['ymin']
    x3 = boundingBox(xleft)['xmax']
    x4 = boundingBox(xleft)['ymax']
    dens2 = boundingBox(xright)['density']
    x5 = boundingBox(xleft)['xmin']
    x6 = boundingBox(xleft)['ymin']
    x7 = boundingBox(xleft)['xmax']
    x8 = boundingBox(xleft)['ymax']
    dens3 = boundingBox(ytop)['density']
    y1 = boundingBox(xleft)['xmin']
    y2 = boundingBox(xleft)['ymin']
    y3 = boundingBox(xleft)['xmax']
    y4 = boundingBox(xleft)['ymax']
    dens4 = boundingBox(ybottom)['density']
    y5 = boundingBox(xleft)['xmin']
    y6 = boundingBox(xleft)['ymin']
    y7 = boundingBox(xleft)['xmax']
    y8 = boundingBox(xleft)['ymax']

    dens.append(dens1)
    dens.append(dens2)
    dens.append(dens3)
    dens.append(dens4)


    if(dens1 > dens2 and dens1 > dens3 and dens1 > dens4):
        if(len(xleft) < 10):
            print("Less than 5 points, max density was: ", numpy.max(dens))
            print("the 4 coordinates (xmin, ymin, xmax, ymax): ", x1, x2, x3, x4)
            return numpy.max(dens)
        convexHull(xleft, dens1)
    if(dens2 > dens1 and dens2 > dens3 and dens2 > dens4):
        if(len(xright) < 10):
            print("Less than 5 points, max density was: ", numpy.max(dens))
            print("the 4 coordinates (xmin, ymin, xmax, ymax): ", x5, x6, x7, x8)
            return numpy.max(dens)
        convexHull(xright, dens2)
    if(dens3 > dens2 and dens3 > dens1 and dens3 > dens4):
        if(len(ytop) < 10):
            print("Less than 5 points, max density was: ", numpy.max(dens))
            print("the 4 coordinates (xmin, ymin, xmax, ymax): ", y1, y2, y3, y4)
            return numpy.max(dens)
        convexHull(ytop, dens3)
    if(dens4 > dens2 and dens4 > dens3 and dens4 > dens1):
        if(len(ybottom) < 10):
            print("Less than 5 points, max density was: ", numpy.max(dens))
            print("the 4 coordinates (xmin, ymin, xmax, ymax): ", y5, y6, y7, y8)
            return numpy.max(dens)
        convexHull(ybottom, dens4)
    
    

def boundingBox(array):
    xmin = array[0][0]
    ymin = array[0][1]
    xmax = array[0][0]
    ymax = array[0][1]


    for row in array:
        if(xmin > row[0]):
            xmin = row[0]
        if(xmax < row[0]):
            xmax = row[0]
        if(ymin > row[1]):
            ymin = row[1]
        if(ymax < row[1]):
            ymax = row[1]

    xlength = xmax - xmin
    ylength = ymax - ymin
    area = xlength * ylength
    numOfPts = len(array)
    density = numOfPts / area
    print("Points in box: ", numOfPts, ", Density: ", density)
    print("The four points of the bounding box are (xmin, ymin, xmax, ymax): ", xmin, ymin, xmax, ymax)
    
    return{'density':density, 'xmin':xmin,'ymin':ymin, 'xmax':xmax, 'ymax':ymax}


array = loadArray("points.csv")

density = boundingBox(array)
maxVal = convexHull(array, 'density')