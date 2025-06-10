import {jwtDecode} from "jwt-decode";

export function Auth() {
  const token = localStorage.getItem("token");

  if (!token) {
    console.warn("No token found");
    return null;
  }

  try {
    const decodedToken = jwtDecode(token);
    const role = decodedToken.role;
    return role;
  } catch (error) {
    console.error("Invalid token:", error);
    return null;
  }
}
