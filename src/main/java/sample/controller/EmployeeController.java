package sample.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sample.context.exception.ServiceException;
import sample.dto.request.employee.EmployeeEditRequestDto;
import sample.dto.request.employee.EmployeeRegisterRequestDto;
import sample.dto.response.EmployeeResponseDto;
import sample.service.EmployeeService;

/**
 * <p>
 * 社員のControllerクラス
 * </p>
 */
@RestController
@RequestMapping("/api/employee")
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
            @PathVariable(value = "employee_id", required = false) String employeeId) {
        try {
            return response(service.getEmployee(employeeId));
        } catch (ServiceException e) {
            return response(e.getStatusCode(), e.getErrorCode(), e.getErrorMessage());
        }
    }

    /**
     * <p>
     * 社員検索API
     * 社員を検索します。
     * </p>
     * 
     * @param employeeId     社員ID
     * @param employeeCode   社員コード
     * @param name           名前
     * @param mail           メールアドレス
     * @param departmentCode 所属部門コード
     * @return 社員一覧
     */
    @GetMapping("/search")
    public List<EmployeeResponseDto> searchEmployee(
            @RequestParam(value = "employee_id", required = false) String employeeId,
            @RequestParam(value = "employee_code", required = false) String employeeCode,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "mail", required = false) String mail,
            @RequestParam(value = "department_code", required = false) String departmentCode) {
        return service.searchEmployee(employeeId, employeeCode, name, mail,
                departmentCode);
    }

    /**
     * <p>
     * 社員登録API
     * 社員を登録します。
     * </p>
     * 
     * @param param 社員登録パラメータ
     * @return 社員登録結果
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@Valid @RequestBody EmployeeRegisterRequestDto param) {
        try {
            return response(service.registerEmployee(param));
        } catch (ServiceException e) {
            return response(e.getStatusCode(), e.getErrorCode(), e.getErrorMessage());
        }
    }

    /**
     * <p>
     * 社員編集API
     * 社員を編集します。
     * </p>
     * 
     * @param param 社員編集パラメータ
     * @return 社員編集結果
     */
    @PatchMapping("/edit/{employee_id}")
    public ResponseEntity<?> editEmployee(
            @Valid @PathVariable(value = "employee_id", required = true) String employeeId,
            @Valid @RequestBody EmployeeEditRequestDto param) {
        try {
            return response(service.editEmployee(employeeId, param));
        } catch (ServiceException e) {
            return response(e.getStatusCode(), e.getErrorCode(), e.getErrorMessage());
        }
    }

    /**
     * <p>
     * 社員削除API
     * 社員を物理削除します。
     * </p>
     * 
     * @param employeeId 社員ID
     * @return 社員削除結果
     */
    @DeleteMapping("/delete/{employee_id}")
    public ResponseEntity<?> deleteEmployee(
            @PathVariable(value = "employee_id", required = true) String employeeId) {
        try {
            return response(service.deleteEmployee(employeeId));
        } catch (ServiceException e) {
            return response(e.getStatusCode(), e.getErrorCode(), e.getErrorMessage());
        }
    }

}
