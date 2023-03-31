package hello.thymeleaf.basic

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Component
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.time.LocalDateTime

@Controller
@RequestMapping("/basic")
class BasicController {

    @GetMapping("/text-basic")
    fun textBasic(model: Model): String {
        model.addAttribute("data", "Hello Spring")
        return "basic/text-basic"
    }

    @GetMapping("/text-unescaped")
    fun textUnescaped(model: Model): String {
        model.addAttribute("data", "<b>Hello Spring</b>")
        return "basic/text-unescaped"
    }

    @GetMapping("/variable")
    fun variable(model: Model): String {
        val userA = User("userA", 10)
        val userB = User("userB", 20)

        val list: MutableList<User> = arrayListOf()
        list.add(userA)
        list.add(userB)

        val map: MutableMap<String, User> = hashMapOf()
        map["userA"] = userA
        map["userB"] = userB

        model.addAttribute("user", userA)
        model.addAttribute("users", list)
        model.addAttribute("userMap", map)

        return "basic/variable"
    }

    @GetMapping("/basic-objects")
    fun basicObjects(model: Model, request: HttpServletRequest, response: HttpServletResponse, session:HttpSession): String {
        model.addAttribute("sessionData", "Hello Session")
        model.addAttribute("request", request)
        model.addAttribute("response", response)
        model.addAttribute("servletContext", request.servletContext)

        session.setAttribute("sessionData", "Hello Session")

        return "basic/basic-objects"
    }

    @GetMapping("/date")
    fun date(model: Model): String {
        model.addAttribute("localDateTime", LocalDateTime.now())
        return "basic/date"
    }

    @GetMapping("/link")
    fun link(model: Model): String {
        model.addAttribute("param1", "data1")
        model.addAttribute("param2", "data2")

        return "basic/link"
    }

    @GetMapping("/literal")
    fun literal(model: Model): String {
        model.addAttribute("data", "Spring!")
        return "basic/literal"
    }

    @GetMapping("/operation")
    fun operation(model: Model): String {
        model.addAttribute("nullData", null)
        model.addAttribute("data", "Spring!")
        return "basic/operation"
    }

    companion object {
        class User(
            var username: String,
            var age: Int
        )

        @Component("helloBean")
        class HelloBean {
            fun hello(data: String): String {
                return "Hello $data"
            }
        }
    }
}