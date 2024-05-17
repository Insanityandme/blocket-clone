"use client";

import { getMyProfile } from "@/utils/api-calls";
import { useEffect, useState } from "react";
import { MyProfileResponseDTO } from "@/types/endpoint-types-incoming";
import { useRouter } from "next/navigation";

function ProfileSkeleton() {
  return (
    <div className="flex animate-pulse flex-col items-center">
      <div className="mb-4 h-4 w-64 rounded-full bg-gray-200 dark:bg-gray-700" />
      <div className="mb-2.5 h-3 w-52 rounded-full bg-gray-200 dark:bg-gray-700" />
      <div className="mb-2.5 h-3 w-48 rounded-full bg-gray-200 dark:bg-gray-700" />
    </div>
  );
}

export default function MyProfile() {
  const router = useRouter();

  const [profile, setProfile] = useState<MyProfileResponseDTO | undefined>();

  useEffect(() => {
    getMyProfile()
      .then((response) => {
        if (response.status === 401) {
          router.push("/login");
        }

        response.json().then((res) => {
          setProfile(res);
        });
      })
      .catch((e) => console.error(e));
  }, [router]);

  return profile ? (
    <div className="w-full bg-blue-200">
      <h1 className="text-center text-3xl">{profile.username}</h1>
      <p className="my-1 text-center">At marketplace since 2013</p>
      <p className="text-center">Date of birth: {profile.dateOfBirth}</p>
    </div>
  ) : (
    <ProfileSkeleton />
  );
}
