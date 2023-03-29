/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cu.edu.unah.sigenuead.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

/**
 *
 * @author claudy
 */
@Embeddable
public class AuthoritiesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "authority")
    private String authority;

    public AuthoritiesPK() {
    }

    public AuthoritiesPK(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        hash += (authority != null ? authority.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuthoritiesPK)) {
            return false;
        }
        AuthoritiesPK other = (AuthoritiesPK) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        if ((this.authority == null && other.authority != null) || (this.authority != null && !this.authority.equals(other.authority))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.AuthoritiesPK[ username=" + username + ", authority=" + authority + " ]";
    }
    
}
