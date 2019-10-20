package test.spring.autoconfig.sampleapp.one;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.spring.autoconfig.sampleapp.one.regs.support.RegistryAccessService;
import test.spring.autoconfig.sampleapp.one.regs.support.RegistryAccessServiceImpl;

@RestController
@AllArgsConstructor
public class Reg1Controller {

    private RegistryAccessService<Reg1> reg1service;

    @GetMapping("/testid")
    public Reg1 getById() {
        return reg1service.getById(1L);
    }
}
