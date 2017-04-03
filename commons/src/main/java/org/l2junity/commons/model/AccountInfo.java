package org.l2junity.commons.model;

import lombok.AllArgsConstructor;
import lombok.Data;

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
	private String accountName;
	private int charCount;
	private List<Long> deleteTimeInfo = new ArrayList<>();
}
