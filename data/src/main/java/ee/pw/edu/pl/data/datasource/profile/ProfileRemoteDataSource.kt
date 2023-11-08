package ee.pw.edu.pl.data.datasource.profile

import ee.pw.edu.pl.data.model.ApiResponseWithHeaders
import ee.pw.edu.pl.data.model.profile.SetProfileInfoRequest

interface ProfileRemoteDataSource {
    suspend fun setInfo(request: SetProfileInfoRequest): ApiResponseWithHeaders<Unit>
}
