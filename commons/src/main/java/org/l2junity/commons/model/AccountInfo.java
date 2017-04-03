package org.l2junity.commons.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ANZO
 * @since 03.04.2017
 */
@Data
@AllArgsConstructor
public class AccountInfo {
	private String accountName;
	private int charCount;
	private List<Long> deleteTimeInfo = new ArrayList<>();
}
