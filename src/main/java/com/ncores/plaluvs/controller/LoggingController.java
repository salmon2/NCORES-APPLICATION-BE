package com.ncores.plaluvs.controller;


import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingController {

    @GetMapping("/")
    public String version() {
        return String.format("Project Version : %s", "0.0.1");
    }

    @GetMapping("/health")
    public String checkHealth() {
        return "healthy";
    }



    @GetMapping("/pytest")
    public String pytest() {
        System.setProperty("python.import.site", "false"); // jython-standalone이 아닐 경우 site 모듈 에러 해결 방안
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.execfile("python/plaluvs-face.py");
        interpreter.exec("start()");

        PyFunction pyFunction = interpreter.get("start", PyFunction.class);

        int a = 10;
        int b = 20;

        PyObject pyobj = pyFunction.__call__();
        System.out.println(pyobj.toString());

        return pyobj.toString();
    }


}

