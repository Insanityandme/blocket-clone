import Image from "next/image";
import { Button } from "@/components/ui/button";
import React from "react";
import { useSearchParams } from "next/navigation";

type Category = {
  name: string;
  image: string;
};
type Categories = Category[];

export default function CategorySelector(props: {
  readonly setProductCategoryName: (_: string | null) => void;
  readonly selectedCategory: string | null;
}) {
  const categories: Categories = [
    { name: "electronics", image: "/images/electronicsIcon.png" },
    { name: "cars", image: "/images/carIcon.png" },
    { name: "phones", image: "/images/phoneIcon.png" },
    { name: "furniture", image: "/images/furnitureIcon.png" },
    { name: "kebab", image: "/images/kebabIcon.png" },
    // Add other categories similarly
  ];

  const searchParams = useSearchParams();
  const category = searchParams.get("category");

  if (category) {
    props.setProductCategoryName(category);
  }

  const reset = () => {
    props.setProductCategoryName(null);
  };

  return (
    <div className="mt-1 flex flex-wrap items-center justify-center">
      {categories.map((category) => (
        <div
          key={category.name}
          className={
            "mx-2 rounded p-1 text-center hover:bg-gray-200 " +
            (props.selectedCategory === category.name && "bg-gray-200")
          }
        >
          <button
            type="button"
            className="flex flex-col items-center"
            onClick={() => props.setProductCategoryName(category.name)}
          >
            <Image
              src={category.image}
              alt={category.name}
              width={24}
              height={24}
            />
            <span>
              {category.name
                .toLowerCase()
                .replace(/\b\w/g, (char) => char.toUpperCase())}
            </span>
          </button>
        </div>
      ))}
      <Button type="button" className="mx-2 bg-red-500" onClick={reset}>
        Reset
      </Button>
    </div>
  );
}
