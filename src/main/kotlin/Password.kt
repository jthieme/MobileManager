import java.util.*

object Password {
    var passwords = arrayOfNulls<String>(0) // Create empty password array
    //var dict = Hashtable<String, String?>()
    var type: String? = null
    var email: String? = null
    var masterPass: String? = null

    fun createMaster(master: String?): String? {
        var master = master
        master = masterPass
        return masterPass
    }

    fun setType(key: String?): String? {
        type = key
        return type
    }

    fun setEmail(eMail: String?): String? {
        email = eMail
        return email
    }

    fun create(newest: String?): String? {

        // Add items to the dictionary
        val passwordData: Any = hashMapOf(
            "serviceName" to type,
            "email" to email,
            "password" to newest
        )

        // Make a copy of original array, then include an additional spot
        passwords = Arrays.copyOf(passwords, passwords.size + 1)

        // Set the new password to the new spot of the array
        passwords[passwords.size - 1] = passwordData.toString()
        return newest
    }

    fun view() {
        println(Arrays.toString(passwords))
    }

    fun newest(newest: String?): String? {

        // Create a new password array
        create(newest)
        return newest
    }

    val array: String
        get() = Arrays.toString(passwords)
}