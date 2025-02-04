package com.xpromus.okane_finance_spring.account

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface AccountRepository : JpaRepository<Account, UUID> {

}