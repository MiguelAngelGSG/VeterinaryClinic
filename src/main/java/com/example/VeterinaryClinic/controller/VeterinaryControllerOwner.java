package com.example.VeterinaryClinic.controller;

import com.example.VeterinaryClinic.model.Owner;
import com.example.VeterinaryClinic.service.VeterinaryServiceOwner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")

public class VeterinaryControllerOwner {

  @Autowired
  VeterinaryServiceOwner veterinaryServiceOwner;

  @PostMapping(path = "/owner")
  public Owner createOwner(@RequestBody Owner newOwner) {
    return veterinaryServiceOwner.createOwner(newOwner);
  }

  @DeleteMapping(path = "/owner/{id}")
  public void deleteOwner(@PathVariable Long id) {
    veterinaryServiceOwner.deleteOwner(id);
  }
}
