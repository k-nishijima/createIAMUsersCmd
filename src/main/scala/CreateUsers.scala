import com.amazonaws._
import com.amazonaws.auth._
import com.amazonaws.services.identitymanagement._
import com.amazonaws.services.identitymanagement.model._
import java.io.PrintWriter
import scala.collection.JavaConversions._
import scala.io.Source
import scala.util.Random

object CreateUsersMain extends App {
  
  val accessKey = "*****"
  val secretKey = "*****"
  val credentials = new BasicAWSCredentials(accessKey,secretKey)
  val iam = new AmazonIdentityManagementClient(credentials)

  def mkpwd() = {
	val word = "ABCDEFGHJKELMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789"
	val r = new Random()
	r.shuffle(word.toList).take(8).mkString
  }

  var f = new PrintWriter("create-users-result.csv")

  for (line <- Source.fromFile("create-users.csv").getLines) {
	val lines = line.split("¥t|,")
   	val groupName = lines(0)
   	val userName = lines(1)
	val password = lines(2) match {
	  case "rand" => mkpwd()
	  case _ => lines(2)
	}
	
	println("try Create %s : %s : %s" format(groupName, userName, password))
	
	val user = iam.createUser(new CreateUserRequest().withUserName(userName))

	println("    success : %s" format(user))

	iam.addUserToGroup(new AddUserToGroupRequest(groupName, userName))
	iam.createLoginProfile(new CreateLoginProfileRequest(userName, password))

	val createAccessKeyResult = iam.createAccessKey(new CreateAccessKeyRequest().withUserName(userName))
	val keys = createAccessKeyResult.getAccessKey()

	f.println("%s,%s,%s,%s,%s" format(groupName, userName, password, keys.getAccessKeyId(), keys.getSecretAccessKey()))
  }

  f.flush
  f.close

}
