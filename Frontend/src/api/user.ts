import axios from "axios";

export type RegisterRequest = {
  fullname: string;
  email: string;
  password: string;
  role: string;
};

export type AuthenticationRequest = {
  email: string;
  password: string;
};

export type AuthenticationResponse = {
  accessToken: string;
  refreshToken: string;
};

const API_URL = import.meta.env.VITE_API_URL;

export const registerUser = async (data: RegisterRequest) => {
  return axios
    .post(`${API_URL}/auth/register`, data)
    .then(({ data }) => {
      const { accessToken, refreshToken } = data;
      localStorage.setItem("jwt", accessToken);
      const expireDate = new Date(Date.now() + 7 * 24 * 60 * 60 * 1000);
      document.cookie = `refreshToken=${refreshToken};expires=${expireDate.toUTCString()}`;
    })
    .catch((err) => {
      console.log(err);
    });
};

export const loginUser = async (data: AuthenticationRequest) => {
  return axios.post(`${API_URL}/auth/authenticate`, data).then(({ data }) => {
    const { accessToken, refreshToken } = data;
    localStorage.setItem("jwt", accessToken);
    const expireDate = new Date(Date.now() + 7 * 24 * 60 * 60 * 1000);
    document.cookie = `refreshToken=${refreshToken};expires=${expireDate.toUTCString()}`;
  });
};

export const logoutUser = async () => {
  return axios.post(`${API_URL}/auth/logout`).then(({ status }) => {
    if (status == 200) {
      localStorage.removeItem("jwt");
      document.cookie = "refreshToken:;";
    }
  });
};
