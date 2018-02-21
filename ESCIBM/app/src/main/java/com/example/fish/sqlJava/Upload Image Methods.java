///* Additional attributes you add to your activity class
//
//	private static final int STORAGE_PERMISSION_CODE = 123; //Don't touch. no idea what it does
//    private int PICK_IMAGE_REQUEST = 1; //image request code. don't touch. don't know what it does.
//    private Bitmap bitmap; //you'll transfer the image into this before base 64 encoding
//
//
//
//
//
//*/
//
//
//
///*   INSIDE ONCREATE
//Requestpermissions(); //quick check for file permission getting. YOU NEED THIS!
//
//		selecteduploadimage= (ImageView) findViewById(R.id.uploadimageview);
////change uploadimageview to whatever the id is for your image viewer
////i'm assuming you're putting one there. if not don't add this line.
//
//
//		//selectuploadbutton is the id of the button to select an image.
//        findViewById(R.id.selectuploadbutton).setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                showFileChooser();
//            }
//        }
//
//
//
//
//*/
//
//
//
//
//
//
//
//
//
//
//
//
//
//	//OTHER STUFF IE methods you'll need for my code to work...
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//            filePath = data.getData();
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath); //I need you to take it as a bitmap.
//                selecteduploadimage.setImageBitmap(bitmap);//If you didn't include an imageviewer, delete this line
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//
//
//    //fun part. image upload, request permissions to obtain something from a file
//    private void Requestpermissions() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == 1) {
//            return; //ie permission was already granted
//        } else {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                //if permission getting forced out
//            }
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
//        }
//
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        if (requestCode == STORAGE_PERMISSION_CODE) {
//
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                //If permission is granted
//            } else {
//                //Displaying another toast if permission is not granted
//                Toast.makeText(this, "Permission was denied.", Toast.LENGTH_LONG).show();
//            }
//
//        }
//    }
//
//
//
//    //method to show file chooser
//    private void showFileChooser() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//    }
//
