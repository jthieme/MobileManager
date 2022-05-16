
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.swing.JFileChooser

object PasswordFiles {
    val MASTER_FILE = "masterPassword.txt"
    var PASS_FILE = "passwords.txt"
    var userDestination = "C:/Users/jomth/userFiles.txt"
    var fileDestination: String? = null
    var file = JFileChooser()
    var passFileName = Scanner(System.`in`)
    var line: String? = null

    fun selectDestination(): String {
        // Open up dialog box to choose where to save files
        file.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        file.showSaveDialog(null)

        // Convert path location to string
        fileDestination = file.selectedFile.toString()
        return fileDestination.toString()
    }

    fun saveMasterPassword(masterPass: String?): String? {
        // Save the master password to the selected destination
        try {
            // Write file destination to the users file destination, with the master password file, containing the master password
            Files.write(Paths.get("$fileDestination/$MASTER_FILE"), masterPass!!.toByteArray())
            // If there's an error
        } catch (e: IOException) {
            // Tell me exactly where to find it
            e.printStackTrace()
        }
        return MASTER_FILE
    }

    fun savePasswordsToFile(passwordArray: String): String {
        // Save the password array to the selected destination
        try {
            // Write files to the users file destination, with the password file, containing the array
            Files.write(Paths.get("$fileDestination/$PASS_FILE"), passwordArray.toByteArray())
            // If there's an error
        } catch (e: IOException) {
            // Tell me exactly where to find it
            e.printStackTrace()
        }
        return PASS_FILE
    }

    fun saveUserFileInfo(): String {
        // If there is no file destination set
        if (fileDestination == null) {
            // Have user select one
            selectDestination()
        } else {
            try {
                Files.write(Paths.get(userDestination), fileDestination!!.toByteArray())
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return userDestination
    }

    fun readFile(searchFile: String?): String? {
        // Turn string into path
        val file = Paths.get(searchFile)
        // Try to read the file
        try {
            Files.newInputStream(file).use { `in` ->
                BufferedReader(InputStreamReader(`in`)).use { reader ->
                    // If file is there and not empty, then read and save its contents
                    while (reader.readLine().also { line = it } != null) {
                        // Return the contents
                        return line
                    }
                    return line
                }
            }
        } catch (x: IOException) {
            // Save the users file destination
            println("Please select where you want your files saved to: ")
            saveUserFileInfo()
        }
        return line
    }
}