package sample.service;

import sample.dto.response.EmployeeResponseDto;

/**
 * <pre>
 * 社員のServiceインターフェース
 * </pre>
 */
public interface EmployeeService {

    /**
     * <p>
     * 社員IDから社員を取得
     * 一致する社員が存在しない場合はエラーを返却
     * </p>
     * 
     * @param employeeId 社員ID
     * @return 社員
     */
    EmployeeResponseDto getEmployee(int employeeId);

}
