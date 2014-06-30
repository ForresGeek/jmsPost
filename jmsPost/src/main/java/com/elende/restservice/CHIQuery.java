package com.elende.restservice;

public class CHIQuery {

	private String Id;
	private String IdType;
	@Override
	public String toString() {
		return "CHIQuery [" + (Id != null ? "Id=" + Id + ", " : "")
				+ (IdType != null ? "IdType=" + IdType : "") + "]";
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getIdType() {
		return IdType;
	}
	public void setIdType(String idType) {
		IdType = idType;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
		result = prime * result + ((IdType == null) ? 0 : IdType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CHIQuery other = (CHIQuery) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		if (IdType == null) {
			if (other.IdType != null)
				return false;
		} else if (!IdType.equals(other.IdType))
			return false;
		return true;
	}
	
	
	
}
