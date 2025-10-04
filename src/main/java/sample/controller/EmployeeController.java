package sample.controller;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
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
import sample.context.Pagination;
import sample.context.exception.ServiceException;
import sample.dto.request.employee.EmployeeEditRequestDto;
import sample.dto.request.employee.EmployeeRegisterRequestDto;
import sample.dto.response.EmployeeResponseDto;
import sample.service.EmployeeService;

/**
 * <pre>
 * 社員のControllerクラス
 * </pre>
 */
@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController extends SampleController {
    // DI
    private final EmployeeService service;

    /**
     * <pre>
     * 社員取得API
     * 社員を1件取得します。
     * </pre>
     * 
     * @param employeeId 社員ID
     * @return 社員
     */
    @GetMapping("/{employee_id}")
    public ResponseEntity<?> getEmployee(
            @PathVariable(value = "employee_id", required = false) String employeeId) {
        try {
            return responseOK(service.getEmployee(employeeId));
        } catch (ServiceException e) {
            return response(e.getStatusCode(), e.getErrorCode(), e.getErrorMessage());
        }
    }

    /**
     * 社員検索API
     * 社員を検索します。
     * 
     * @param employeeId     社員ID
     * @param name           社員名
     * @param departmentCode 部署コード
     * @param postCode       役職コード
     * @param enteredAtFrom  入社日（開始）
     * @param enteredAtTo    入社日（終了）
     * @param status         ステータス
     */
    @GetMapping("/search")
    public Pagination<EmployeeResponseDto> searchEmployee(
            @RequestParam(value = "employee_id", required = false) String employeeId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "department_code", required = false) String departmentCode,
            @RequestParam(value = "post_code", required = false) String postCode,
            @RequestParam(value = "entered_at_from", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enteredAtFrom,
            @RequestParam(value = "entered_at_to", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enteredAtTo,
            @RequestParam(value = "status", required = false) String status) {
        return service.searchEmployee(employeeId, name, departmentCode, postCode, enteredAtFrom, enteredAtTo, status);
    }

    /**
     * <pre>
     * 社員登録API
     * 社員を登録します。
     * </pre>
     * 
     * @param param 社員登録パラメータ
     * @return 社員登録結果
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@Valid @RequestBody EmployeeRegisterRequestDto param) {
        try {
            return responseCreate(service.registerEmployee(param));
        } catch (ServiceException e) {
            return response(e.getStatusCode(), e.getErrorCode(), e.getErrorMessage());
        }
    }

    /**
     * <pre>
     * 社員編集API
     * 社員を編集します。
     * </pre>
     * 
     * @param param 社員編集パラメータ
     * @return 社員編集結果
     */
    @PatchMapping("/edit/{employee_id}")
    public ResponseEntity<?> editEmployee(
            @Valid @PathVariable(value = "employee_id", required = true) String employeeId,
            @Valid @RequestBody EmployeeEditRequestDto param) {
        try {
            return responseOK(service.editEmployee(employeeId, param));
        } catch (ServiceException e) {
            return response(e.getStatusCode(), e.getErrorCode(), e.getErrorMessage());
        }
    }

    /**
     * <pre>
     * 社員削除API
     * 社員を物理削除します。
     * </pre>
     * 
     * @param employeeId 社員ID
     * @return 社員削除結果
     */
    @DeleteMapping("/delete/{employee_id}")
    public ResponseEntity<?> deleteEmployee(
            @PathVariable(value = "employee_id", required = true) String employeeId) {
        try {
            return responseOK(service.deleteEmployee(employeeId));
        } catch (ServiceException e) {
            return response(e.getStatusCode(), e.getErrorCode(), e.getErrorMessage());
        }
    }

}
