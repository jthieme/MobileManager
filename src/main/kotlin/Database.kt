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


    fun init() {
        val serviceAccount = FileInputStream("passwordmanager-5d592-firebase-adminsdk-sb8vz-8129857f32.json")
        val credentials = GoogleCredentials.fromStream(serviceAccount)
        val options = FirebaseOptions.Builder().setCredentials(credentials).build()
        FirebaseApp.initializeApp(options)

        println("Initialized Firestore")
    }

    fun saveAllUserData(userName: String?, passwordData: Any, masterPass: String?) {
        val userData: Any = hashMapOf(
            "accountInfo" to passwordData,
            "masterPass" to masterPass,
        )
        if (userName != null) {
            val result = users.document(userName).set(userData)
            result.get()
        }
    }

    fun readDatabase(userName: String) {
        val future: ApiFuture<DocumentSnapshot> = users.document(User.getUserName()).get()
        val document = future.get()


        if (document.exists()) {
            Password.create(document.getData().toString())
        } else {
            println("No data exists here.")
        }
    }

    fun readMaster(userName: String) : String {
        val userFields = users.document(userName).get().get()
        if (userFields.exists()) {
        //if (userFields.equals("masterPass")) {
            val confirmMaster = userFields["masterPass"] as String
            return confirmMaster
        }
        return "Error"
    }

    fun deleteAccount(userName: String) {
        val result = users.document(userName).delete()
        result.get()
    }

}