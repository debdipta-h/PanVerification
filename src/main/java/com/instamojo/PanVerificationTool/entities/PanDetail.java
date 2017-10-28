package com.instamojo.PanVerificationTool.entities;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the PAN_DETAILS database table.
 * 
 */
@Entity
@Table(name="PAN_DETAILS")
@NamedQuery(name="PanDetail.findAll", query="SELECT p FROM PanDetail p")
public class PanDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PAN_ID")
	private String panId;

	@Column(name="DATE_OF_BIRTH")
	private Object dateOfBirth;

	@Column(name="FEEDBACK_POINT")
	private BigDecimal feedbackPoint;

	@Column(name="ISSUE_DATE")
	private Object issueDate;

	@Column(name="PAN_USER_NAME")
	private String panUserName;

	@Column(name="VERIFICATION_AGENT_ID")
	private BigDecimal verificationAgentId;

	//bi-directional many-to-one association to UserDetail
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserDetail userDetail;

	public PanDetail() {
	}

	public String getPanId() {
		return this.panId;
	}

	public void setPanId(String panId) {
		this.panId = panId;
	}

	public Object getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Object dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public BigDecimal getFeedbackPoint() {
		return this.feedbackPoint;
	}

	public void setFeedbackPoint(BigDecimal feedbackPoint) {
		this.feedbackPoint = feedbackPoint;
	}

	public Object getIssueDate() {
		return this.issueDate;
	}

	public void setIssueDate(Object issueDate) {
		this.issueDate = issueDate;
	}

	public String getPanUserName() {
		return this.panUserName;
	}

	public void setPanUserName(String panUserName) {
		this.panUserName = panUserName;
	}

	public BigDecimal getVerificationAgentId() {
		return this.verificationAgentId;
	}

	public void setVerificationAgentId(BigDecimal verificationAgentId) {
		this.verificationAgentId = verificationAgentId;
	}

	public UserDetail getUserDetail() {
		return this.userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

}