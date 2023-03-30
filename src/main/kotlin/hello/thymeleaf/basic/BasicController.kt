package hello.thymeleaf.basic

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

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

    companion object {
        class User(
            var username: String,
            var age: Int
        )
    }
}