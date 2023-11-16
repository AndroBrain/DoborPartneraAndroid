package ee.pw.edu.pl.data.datasource.profile.remote

import ee.pw.edu.pl.data.model.ApiResponseWithHeaders
import ee.pw.edu.pl.data.model.profile.GetProfileInfoResponse
import ee.pw.edu.pl.data.model.profile.SetProfileInfoRequest

interface ProfileRemoteDataSource {
    suspend fun setInfo(request: SetProfileInfoRequest): ApiResponseWithHeaders<Unit>
    suspend fun getInfo(): ApiResponseWithHeaders<GetProfileInfoResponse>
}
