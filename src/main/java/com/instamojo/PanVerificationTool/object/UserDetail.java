package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the USER_DETAILS database table.
 * 
 */
@Entity
@Table(name="USER_DETAILS")
@NamedQuery(name="UserDetail.findAll", query="SELECT u FROM UserDetail u")
public class UserDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private String userId;

	@Column(name="REVIEW_POINTS")
	private BigDecimal reviewPoints;

	@Column(name="USER_NAME")
	private String userName;

	//bi-directional many-to-one association to PanDetail
	@OneToMany(mappedBy="userDetail")
	private List<PanDetail> panDetails;

	public UserDetail() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getReviewPoints() {
		return this.reviewPoints;
	}

	public void setReviewPoints(BigDecimal reviewPoints) {
		this.reviewPoints = reviewPoints;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<PanDetail> getPanDetails() {
		return this.panDetails;
	}

	public void setPanDetails(List<PanDetail> panDetails) {
		this.panDetails = panDetails;
	}

	public PanDetail addPanDetail(PanDetail panDetail) {
		getPanDetails().add(panDetail);
		panDetail.setUserDetail(this);

		return panDetail;
	}

	public PanDetail removePanDetail(PanDetail panDetail) {
		getPanDetails().remove(panDetail);
		panDetail.setUserDetail(null);

		return panDetail;
	}

}