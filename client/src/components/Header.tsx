import { Box, Flex, HStack, Text } from "@chakra-ui/react";
import { Outlet } from "react-router";
import { Button } from "./ui/button";
import { ColorModeButton } from "./ui/color-mode";

const Header = () => {
  return (
    <>
      <header>
        <Flex justify={"center"} px={"3.75rem"}>
          <HStack
            justifyContent={"space-between"}
            maxWidth={"62.5rem"}
            width={"full"}
          >
            <Button variant={"plain"}>
              <Text textStyle={"lg"}>Crash Point</Text>
            </Button>
            <ColorModeButton />
          </HStack>
        </Flex>
      </header>
      <Outlet />
    </>
  );
};

export default Header;
