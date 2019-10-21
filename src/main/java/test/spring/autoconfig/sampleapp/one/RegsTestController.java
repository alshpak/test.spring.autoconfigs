package test.spring.autoconfig.sampleapp.one;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.spring.autoconfig.sampleapp.one.regs.support.RegistryAccessService;
import test.spring.autoconfig.sampleapp.one.regs.support.RegistryAccessServiceImpl;

@RestController
public class RegsTestController {

    @Autowired
    private RegistryAccessService<Reg1> reg1service;

    @Autowired
    private RegistryAccessService<Reg2> reg2service;

    @GetMapping("/testid")
    public Reg1 getById() {
        return reg1service.getById(1L);
    }

    @GetMapping("/reg2my/{id}")
    public Reg2 getByIdReg2(@PathVariable  Long id) {
        return reg2service.getById(id);
    }
}
