package com.roberto.codember.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ValidPassword {

  private long pass;
  private int position;

}
