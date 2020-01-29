package utils

import java.util.{Date, HashMap, UUID}

import pdi.jwt.JwtSession


object jwt{
  def renew(session:JwtSession): String = {
    session.refresh().serialize
  }
}
