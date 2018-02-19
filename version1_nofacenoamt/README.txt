Hello. initialise your android project as per normal.
This is because gradle doesn't allow the project to work on more comps.
So copying over the entire project won't work.
The important parts of my file are added here.


First: in AndroidManifest.xml. You can  replace your current android manifest.xml file with mine. But you'll lose your original default activity.
But that does not mean you can't restore it. Just look into the file and you'll see how to do it (default activity)

Second: you need to change the package inside the manifest to reflect your current package. Be careful with this.

Third: you need to add all the java stuff in, and all my res file items in. this is to make my stuff work. you don't necessarily need it. but you NEED my strings.xml