import com.google.api.services.storage.model.Bucket.Encryption
import java.util.*

// 1. Add a password
// 2. Edit an existing password
// 3. Delete an existing password
// 4. Save all passwords in an encrypted folder on their local machine
// 5. Allow the passwords to be decrypted when the correct Master password is entered
// 6. View all the passwords as long as the master password has been provided
// TODO:
// Create User class -> NO ARRAYS
// Password class only holds a single password
// User has a Password
// User class should have a mutableList
// Decrypt passwords once the master password has been entered
// Save and encrypt passwords to users selected destination
object PasswordManager {
    // Set the private member variables
    var master = Scanner(System.`in`)   // Initialize scanner object for master password
    var masterPassword : String? = null // Initialize master password variable
    var user = Scanner(System.`in`)     // Initialize scanner object for master password
    var userName : String? = null       // Initialize master password variable
    var choose = Scanner(System.`in`)   // Get user input for options
    var choice: String? = null
    var checkMaster: String? = null
    var enterMaster: String? = null

    fun welcome() {
        // Display welcome screen
        println("Welcome to the Password Manager!")
    }

    fun carrot() {
        // Display carrot
        print("\n> ")
    }

    fun createMasterPassword(): String? {

        // Display to user the need for master password
        println("Please create your master password.")

        // Display carrot
        carrot()

        // Set masterPassword to receive user input
        masterPassword = master.next()
        User.setMaster(masterPassword.toString())
        //Password.createMaster(masterPassword)

        // Return the masterPassword
        return masterPassword
    }

    fun createUserName() {
        // Display to user the need for user name
        println("Please create your username ")

        // Display carrot
        carrot()

        // Set userName to receive user input
        userName = user.next()

        // Set user name for the database
        User.setUserName(userName.toString())
        //Database.setUserName(userName.toString())
    }

    fun menu() {
        // Instruct user to make a selection
        println("\nPlease select what you would like to do: ")

        // Display available options
        println("1. Add a new password" +
                "\n2. Edit an existing password" +
                "\n3. Delete an existing password" +
                "\n4. View passwords" +
                "\n5. Delete Account" +
                "\n6. Save & Exit")

        // Display carrot
        carrot()
    }

    @JvmStatic
    fun main() {
        // Get the database started
       // Database.init()

        // Display welcome screen
        welcome()
        println("Do you have a user name? (Y/N) ")
        carrot()
        val hasUserName = choose.next()

        // If it's the users first time with the program
        if (hasUserName == "N" || hasUserName == "n") {
            // Create the user name
            createUserName()

            // Then create their master password
            createMasterPassword()
        // If they already have a user name
        } else if (hasUserName == "Y" || hasUserName == "y"){
            // Check to see if the master password matches
            println("Please enter your username: ")
            carrot()
            userName = choose.next()
            // If there really is a user name
            if (userName != null) {
                // Save it
                User.setUserName(userName.toString())
            }
            // Read the database to check for user
            val checkUserInfo = Database.readDatabase(userName.toString())

            // Get the master password
            checkMaster = Database.readMaster(userName.toString())

            // Prompt user for their master password
            println("Please enter your master password: ")
            carrot()
            enterMaster = choose.next()
            masterPassword = enterMaster

            // Save the master password
            Password.createMaster(masterPassword)

        // While the attempted and stored passwords do not match
        while (!checkMaster.toString().equals(enterMaster.toString())) {

            // Keep trying again
            println("Incorrect password. Try again.")
            carrot()

            // New attempt
            enterMaster = choose.next()
            masterPassword = enterMaster
        }
        // If they do match
        if (checkMaster.toString().equals(enterMaster.toString())) {

            // Welcome back
            println("Welcome back")
        }

    }
        while ("6" != choice) {
            // Display available options
            menu()
            choice = choose.next()

            // If user wants to add a password to the collection,
            if ("1" == choice) {
                println("Enter the service name: ")
                carrot()
                val serviceName = choose.next()
                Password.setType(serviceName)
                println("Enter email for $serviceName: ")
                carrot()
                val email = choose.next()
                Password.setEmail(email)

                // Prompt them to enter a password
                println("\nEnter your password: ")
                carrot()

                // Allow them to type in their password
                val newPassword = choose.next()

                // Get the new password
                Password.newest(newPassword)


                // Reset the choice
                choice = null
            }
            if ("2" == choice) {
                println("Please enter the service for the password you would like to edit: ")
                carrot()
                println("Do Nothing")
                /*val edit = choose.next()
                val documentResult = Database.readDatabase(userName.toString())
                if (documentResult.equals(edit)) {

                }*/
                println("Do Nothing")

                // Reset the choice
                choice = null
            }
            if ("3" == choice) {
                println("Delete which password? ")
                carrot()
                println("Do Nothing")

                // Reset the choice
                choice = null
            }
            if ("4" == choice) {
                // View all the passwords
                println("Here's a list of your passwords and the service they belong to: ")
                Password.view()
            }
            if ("5" == choice) {
                // Check to see if the user really wants to delete their account
                println("Are you sure you want to delete your account? (Y/N)")
                carrot()
                val shouldDelete = choose.next()

                // If yes (Y or y)
                if (shouldDelete == "Y" || shouldDelete == "y") {

                    // Display sad message
                    println("We're sorry to see you go.\nGoodbye.")

                    // Delete the account
                    Database.deleteAccount(userName.toString())

                    // End the program
                    break
                } else {
                    continue
                }
            }
            if ("6" == choice) {
                // Save the passwords at that location, with the provided name
                Database.saveAllUserData(User.getUserName(), Password.array, masterPassword)

                // Tell user where their files have been saved to
                println("Your information has been saved successfully!")
                println("\nGoodbye!")

                // End the Program
                break
            }
        }
    }
}