package sample.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sample.exception.ServiceException;
import sample.service.EmployeeService;

/**
 * <pre>
 * 社員のControllerクラス
 * </pre>
 */
@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController extends SampleController {
    // DI
    private final EmployeeService service;

    /**
     * <p>
     * 社員取得API
     * 社員を1件取得します。
     * </p>
     * 
     * @param employeeId 社員ID
     * @return 社員
     */
    @GetMapping("/{employee_id}")
    public ResponseEntity<?> getEmployee(
            @PathVariable(value = "employee_id", required = false) int employeeId) {
        try {
            return response(service.getEmployee(employeeId));
        } catch (ServiceException e) {
            return response(e.getStatusCode(), e.getErrorCode(), e.getErrorMessage());
        }
    }

}
