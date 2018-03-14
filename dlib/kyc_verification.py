import sys
import os
import dlib
import glob
import time
import numpy as np
from skimage import io
'''
Call the program with "python kyc_verification.py path_to_face_image_1 path_to_face_image_2"
Predictor file and dlib file must be in the same folder.
'''
startTime = time.time()
if (len(sys.argv) == 3):
	face_path1 = sys.argv[1]
	face_path2 = sys.argv[2]
else:
	print("Call this program like this:\n"
	      "python ./kyc_verification.py path_to_face_image_1 path_to_face_image_2\n")
	exit()

# Load all the models we need: a detector to find the faces, a shape predictor
# to find face landmarks so we can precisely localize the face, and finally the
# face recognition model.
predictor_path = "shape_predictor_5_face_landmarks.dat"
face_rec_model_path = "dlib_face_recognition_resnet_model_v1.dat"
detector = dlib.get_frontal_face_detector()
sp = dlib.shape_predictor(predictor_path)
facerec = dlib.face_recognition_model_v1(face_rec_model_path)

img1 = io.imread(face_path1)
img2 = io.imread(face_path2)

# Ask the detector to find the bounding boxes of each face. The 1 in the
# second argument indicates that we should upsample the image 1 time. This
# will make everything bigger and allow us to detect more faces.dets1 = detector(img1,1)
dets1 = detector(img1,1)
dets2 = detector(img2,1)

# Get the landmarks/parts for the face in box dets1[0].
landmark1 = sp(img1, dets1[0])
landmark2 = sp(img2, dets2[0])


# Compute the 128D vector that describes the face in img identified by
# shape.  In general, if two face descriptor vectors have a Euclidean
# distance between them less than 0.6 then they are from the same
# person, otherwise they are from different people. Here we just print
# the vector to the screen.
face_descriptor1 = facerec.compute_face_descriptor(img1, landmark1)
face_descriptor2 = facerec.compute_face_descriptor(img2, landmark2)

npFace_descriptor1 = np.array([p for p in face_descriptor1])
npFace_descriptor2 = np.array([p for p in face_descriptor2])

euclideanDistance = np.linalg.norm(npFace_descriptor1 - npFace_descriptor2, axis=0)
print("Euclidean Distance: {:f}".format(euclideanDistance))
if (euclideanDistance < 0.45):
	print("TRUE")
else:
	print("FALSE")
endTime = time.time()
print("Time elapsed: {:f}".format(endTime - startTime))

