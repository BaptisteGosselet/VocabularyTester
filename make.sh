#Compiler
javac -sourcepath src -d bin src/Main.java 

#Générer le jar
jar cfe VocabularyTest.jar Main -C bin ./

#Lancer le projet
java -jar VocabularyTest.jar 

#Supprimer le dossier bin
rm -rf bin

#Supprimer le jar
rm VocabularyTest.jar
