package ee.pw.edu.pl.data.datasource.account.remote

import ee.pw.edu.pl.data.model.ApiResponse
import ee.pw.edu.pl.data.model.profile.remote.GetProfileInfoResponse
import ee.pw.edu.pl.data.model.profile.remote.SetProfileInfoRequest

interface AccountRemoteDataSource {
    suspend fun setInfo(request: SetProfileInfoRequest): ApiResponse<Unit>
    suspend fun getInfo(): ApiResponse<GetProfileInfoResponse>
}
