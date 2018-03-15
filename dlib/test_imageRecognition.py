'''
Run this test with folder directory structure as such:
current_dir
	faces
		name1
			image1.jpg
			image2.jpg
		name2
			image1.jpg
			image2.jpg
		name3
			image3.jpg
			image3.jpg
'''


import os
import dlib
import glob
import numpy as np
import itertools
import pytest
from skimage import io

predictor_path = "shape_predictor_5_face_landmarks.dat"
face_rec_model_path = "dlib_face_recognition_resnet_model_v1.dat"
detector = dlib.get_frontal_face_detector()
sp = dlib.shape_predictor(predictor_path)
facerec = dlib.face_recognition_model_v1(face_rec_model_path)
threshold = 0.45

'''Change to faces directory'''

def prepareImage():
	cwd = os.getcwd()
	cwd = os.path.join(cwd, "faces")
	os.chdir(cwd)
	allImageList = []
	directoryList = os.listdir(cwd)
	for i in directoryList:
		cwd2 = os.path.join(cwd, i)
		directoryList2 = os.listdir(cwd2)
		for i2 in range(len(directoryList2)):
			directoryList2[i2] = os.path.join(i, directoryList2[i2])
		allImageList.append(directoryList2)
	return allImageList

@pytest.fixture(scope="module")
def allImageList():
	return prepareImage()


def test_withinSubject(allImageList):
	print("")
	for i in range(len(allImageList)):
		innerImages = allImageList[i]
		if (len(innerImages) <= 1): continue
		combinations = itertools.combinations(innerImages,2)
		for pair in combinations:
			result = verifyImage(pair[0],pair[1])
			print((result, pair[0], pair[1]))
			assert result

def test_betweenSubject(allImageList):
	pair = itertools.combinations(allImageList,2)
	for (a,b) in pair:
		crossMatch = itertools.product(a,b)
		for pair in crossMatch:
			result = verifyImage(pair[0],pair[1])
			print((result, pair[0], pair[1]))
			assert result == False

def verifyImage(face_path1, face_path2):
	img1 = io.imread(face_path1)
	img2 = io.imread(face_path2)

	dets1 = detector(img1,1)
	dets2 = detector(img2,1)

	landmark1 = sp(img1, dets1[0])
	landmark2 = sp(img2, dets2[0])

	face_descriptor1 = facerec.compute_face_descriptor(img1, landmark1)
	face_descriptor2 = facerec.compute_face_descriptor(img2, landmark2)

	npFace_descriptor1 = np.array([p for p in face_descriptor1])
	npFace_descriptor2 = np.array([p for p in face_descriptor2])

	euclideanDistance = np.linalg.norm(npFace_descriptor1 - npFace_descriptor2, axis=0)
	if (euclideanDistance < threshold):
		return True
	else:
		return False























