package com.toyr.msa.msahello;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
        import org.springframework.jdbc.core.JdbcTemplate;
        import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@SpringBootApplication(scanBasePackages = "com.toyr.msa")
public class MsaHelloApplication {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/hello")
//    @RequestMapping（"/hello")
    @RequestMapping(method = RequestMethod.GET, path = "/hello")
    public String hello() {
        String name = jdbcTemplate.queryForObject(
                "SELECT name FROM customer WHERE id = 1", String.class);
        return "Hello " + name;
    }

    public static void main(String[] args) {
        SpringApplication.run(MsaHelloApplication.class, args);
    }

}
