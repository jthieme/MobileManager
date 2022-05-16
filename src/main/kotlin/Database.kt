import com.google.api.core.ApiFuture
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.DocumentSnapshot
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import java.io.FileInputStream

object Database {
    val db = FirestoreClient.getFirestore()
    val users = db.collection("users")
    var uName = ""


    fun init() {
        val serviceAccount = FileInputStream("passwordmanager-5d592-firebase-adminsdk-sb8vz-8129857f32.json")
        val credentials = GoogleCredentials.fromStream(serviceAccount)
        val options = FirebaseOptions.Builder().setCredentials(credentials).build()
        FirebaseApp.initializeApp(options)

        println("Initialized Firestore")
    }

    fun setUserName(userName: String) {
        uName = userName
    }

    fun getUserName() : String {
        return uName
    }

    fun saveAllUserData(userName: String?, passwordData: String, masterPass: String?) {
        println("saveAllUserData: $userName, $passwordData, $masterPass")
        val userData = hashMapOf(
            "accountInfo" to passwordData,
            "masterPass" to masterPass,
        )
        if (userName != null) {
            users.document(userName).set(userData as Map<String, Any>)
        }
    }

    fun readDatabase(userName: String) {
        val future: ApiFuture<DocumentSnapshot> = users.document(getUserName()).get()
        val document = future.get()
        println(document)

        if (document.exists()) {
            Password.create(document.getData().toString())
        } else {
            println("No data exists here.")
        }
    }

    fun readMaster(userName: String) : String {
        val userFields = users.document(userName).get()
        if (userFields.equals("masterPass")) {
            val confirmMaster = userFields.get().toString()
            return confirmMaster
        }
        return "Error"
    }

}