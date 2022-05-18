import com.google.api.core.ApiFuture
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.DocumentSnapshot
import com.google.cloud.firestore.WriteResult
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import java.io.FileInputStream


fun main() {
    val serviceAccount = FileInputStream("passwordmanager-5d592-firebase-adminsdk-sb8vz-8129857f32.json")
    val credentials = GoogleCredentials.fromStream(serviceAccount)
    val options = FirebaseOptions.Builder().setCredentials(credentials).build()
    FirebaseApp.initializeApp(options)
    val db = FirestoreClient.getFirestore()
    println("Initialized Firestore")
/*
    val users = db.collection("users")
    val future: ApiFuture<DocumentSnapshot> = users.document("name").get()
    val document = future.get()

    if (document.exists()) {
        println("Document data: " + document.getData())
    } else {
        println("No data exists here.")
    }

    val passwordData: Map<String, Any> = hashMapOf(

        "serviceName" to "Google",
        "email" to "jomthieme@gmail.com",
        "password" to "orange",
    )

    val testData: Map<String, Any> = hashMapOf(
        "accountInfo" to passwordData,
        "masterPass" to "masatater",
    )
    users.document("testUser").set(testData)*/

    PasswordManager.main()





}
