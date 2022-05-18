object User {
   private var userName : String = ""
   private var masterPass : String = ""
   private var accountInfo = mutableListOf<String>()

   fun setUserName(_userName : String) {
       userName = _userName
   }

   fun setMaster(master : String) {
       masterPass = master
   }

   fun setAccountInfo(accountList : String) {
       accountInfo.add(accountList)
   }

   fun getAccountInfo() : MutableList<String> {
       return accountInfo
   }

   fun getMaster() : String {
       return masterPass
   }

   fun getUserName() : String {
       return userName
   }
}