import { createSlice } from '@reduxjs/toolkit';



export const loinslice = createSlice({
  initialState: false,
  name: "isLogin",
  reducers: {
    logined: (state) =>
      state = true
    ,
    logout: (state) =>
      // console.log("fasle");
      state = false
    ,
  },
})


export const { logined, logout } = loinslice.actions;

export default loinslice.reducer;