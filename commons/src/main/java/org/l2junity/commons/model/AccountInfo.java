package org.l2junity.commons.model;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ANZO
 * @since 03.04.2017
 */
@Data
@AllArgsConstructor
public class AccountInfo implements Serializable {
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = 1L;

	private String accountName;
	private int charCount;
	private List<Long> deleteTimeInfo = new ArrayList<>();
}
