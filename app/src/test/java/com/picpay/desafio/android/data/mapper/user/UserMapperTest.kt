package com.picpay.desafio.android.data.mapper.user

import com.picpay.desafio.android.data.local.db.Mocks
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
import org.junit.Test
import kotlin.test.assertTrue

class UserMapperTest {

    @Test
    fun `transform UserEntity into User`() {
        assertTrue {
            UserMapper.transform(Mocks.userEntityMock) ==  Mocks.userMock
        }
    }

    @Test
    fun `transform UserEntity List into User List and compare if values are same`() {
        val userEntityListMock = Mocks.userEntityListMock
        val userList = UserMapper.transformList(userEntityListMock)

        userList.forEachIndexed { index, user ->
            run {
                MatcherAssert.assertThat(user.id, `is`(userEntityListMock[index].id))
                MatcherAssert.assertThat(user.img, `is`(userEntityListMock[index].img))
                MatcherAssert.assertThat(user.name, `is`(userEntityListMock[index].name))
                MatcherAssert.assertThat(user.username, `is`(userEntityListMock[index].username))
            }
        }
    }

}